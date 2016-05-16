package View.DefaultUI;
import Model.*;
import Model.Entity.Employment;
import Model.SQlRepo.EmploymentDAO;
import Model.SQlRepo.SQLServerConnection;
import com.vaadin.ui.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Abeer on 4/13/2016.
 * modified by Simon on 2016/04/28 to make it work with real employment from online DB
 */
public class DeletionConfirmationWindow extends Window {

    Button yesButton = new Button("Yes");
    Button noButton = new Button("No");
    HorizontalLayout actions = new HorizontalLayout(yesButton, noButton);
    VerticalLayout content = new VerticalLayout();
    private Employment selectedEmployment;

    public DeletionConfirmationWindow(DeletionLogModel logModel, Grid grid) {
        super("Delete employments"); // Set window caption;
        init(grid);

        yesButton.addClickListener(e -> {

            Grid.MultiSelectionModel selection = (Grid.MultiSelectionModel) grid.getSelectionModel();

            try {
                Connection connect = SQLServerConnection.getInstance();
                EmploymentDAO daoEmployment = new EmploymentDAO(SQLServerConnection.getInstance());
                for (Object itemId: selection.getSelectedRows()) {
                    selectedEmployment = (Employment)itemId;
                    // delete employment from db
                    daoEmployment.delete(selectedEmployment);
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
                connect.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }

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


