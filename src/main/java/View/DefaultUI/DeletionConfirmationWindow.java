package View.DefaultUI;
import Model.Entity.Employment;
import Presenter.DeletingEmploymentsPresenter;
import com.vaadin.ui.*;

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

    /**
     * Constructor of the confirmation window for employment deletion
     * @param grid
     * @param employmentsPresenter the presenter of the window that called that "subwindow"
     */
    public DeletionConfirmationWindow( Grid grid, DeletingEmploymentsPresenter employmentsPresenter) {
        super("Delete employments"); // Set window caption;
        init(grid);

        yesButton.addClickListener(e -> {
            Grid.MultiSelectionModel selection = (Grid.MultiSelectionModel) grid.getSelectionModel();
            for (Object itemId: selection.getSelectedRows()) {
                selectedEmployment = (Employment)itemId;
                // delete employment from db
                employmentsPresenter.deleteEmploymentFromDAO(selectedEmployment);
                grid.getContainerDataSource().removeItem(itemId);
                // create log when the employment is deleted
                employmentsPresenter.createLog(selectedEmployment);
            }
            grid.getSelectionModel().reset();
            close();
        });

        content.addComponents(new Label("Are you sure you want to delete the selected employments?"), actions);
        setContent(content);
    }

    /**
     * Method to init the grid
     * @param grid teh grid to init
     */
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


