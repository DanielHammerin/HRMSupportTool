package View.DefaultUI;
import Model.*;
import Model.Entity.Employment;
import Model.SQlRepo.EmploymentDAO;
import Model.SQlRepo.SQLServerConnection;
import Presenter.DeletingEmploymentsPresenter;
import com.vaadin.ui.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Abeer on 4/13/2016.
 * modified by Simon on 2016/04/28 to make it work with real employment from online DB
 */
public class DeletionConfirmationWindow extends Window {

    private Button yesButton = new Button("Yes");
    private Button noButton = new Button("No");
    private HorizontalLayout actions = new HorizontalLayout(yesButton, noButton);
    private VerticalLayout content = new VerticalLayout();
    private Employment selectedEmployment;
    private DeletingEmploymentsPresenter deletingEmploymentsPresenter;

    public DeletionConfirmationWindow(DeletionLogModel logModel, Grid grid, DeletingEmploymentsPresenter deletingEmploymentsPresenter) {
        super("Delete employments"); // Set window caption;
        this.deletingEmploymentsPresenter = deletingEmploymentsPresenter;
        init(grid);

        yesButton.addClickListener(e -> {

            Grid.MultiSelectionModel selection = (Grid.MultiSelectionModel) grid.getSelectionModel();

            for (Object itemId: selection.getSelectedRows()) {
                selectedEmployment = (Employment)itemId;
                // delete employment from db
                deletingEmploymentsPresenter.deleteEmploymentFromDAO(selectedEmployment);
                grid.getContainerDataSource().removeItem(itemId);
                // create log when the employment is deleted
                if(!logModel.createLog(String.valueOf(UI.getCurrent().getSession().getAttribute("user")), selectedEmployment, new Date())){
                   // showing error  notification when error happens in saving deletion log
                    new Notification("Deletion log is not saved", Notification.TYPE_ERROR_MESSAGE)
                            .show(getUI().getPage());
                    return;
                }

            }
            grid.getSelectionModel().reset();
            close();

            Notification.show("Deletion is done successfully");

        });


        content.addComponents(new Label("Are you sure you want to delete the selected employees?"), actions);

        setContent(content);
    }

    private void init(Grid grid) {
        center();
        actions.setSpacing(true);
        setModal(true);
        setClosable(false);
        setResizable(false);
        content.setMargin(true);
        content.setSpacing(true);
        noButton.addClickListener(e -> {
            close();
            grid.getSelectionModel().reset();
        });
    }

}


