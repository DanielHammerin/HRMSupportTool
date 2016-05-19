package View.DefaultUI;

import Presenter.EmploymentsPresenter;
import View.DatabaseSelection.DatabaseSelectionWindow;
import View.LogUI.LogGrid;
import View.LoginUI.LoginWindow;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import View.Buttons.LogoutOption;

import java.io.IOException;
/**
 * Created by Abeer on 5/10/2016.
 * DeletingEmploymentWindow show the main window for deleting employment, show employment info,
 * edit employment and delete employment
 * edition and adding employment is only enabled for admin user.
 */
@SpringView(name = DeletingEmploymentsWindow.VIEW_NAME)
public class DeletingEmploymentsWindow extends VerticalLayout implements View {
    public static final String VIEW_NAME = "default";
    private HorizontalLayout ButtonsLayout;
    private Panel panel;
    private LogoutOption logoutHLayout;
    private Button viewlogButton;
    private Button viewEmploymentsButton;
    private Button viewDatabaseSelectionButton;
    private Button addNewEmploymentButton;
    private boolean isAdmin ;
    private EmploymentsPresenter employmentsPresenter;

    public DeletingEmploymentsWindow() throws IOException {
        ButtonsLayout = new HorizontalLayout();
        panel = new Panel();
        // adding view buttons to the window
        viewlogButton = new Button("View Log");
        viewEmploymentsButton = new Button("View Current Employments");
        viewDatabaseSelectionButton = new Button("View Database Selection");
        addNewEmploymentButton = new Button("Add New Employment");
        // adding layout for logout option
        logoutHLayout = new LogoutOption(String.valueOf(UI.getCurrent().getSession().getAttribute("user")));
    }
    private void addWindowComponents (){
       // adding employmentGrid as thr default view
        panel.setContent( new EmploymentsGrid(employmentsPresenter));
        viewEmploymentsButton.setVisible(false);
        viewDatabaseSelectionButton.setVisible(true);
        //making adding employment only visible for admin
        addNewEmploymentButton.setVisible(isAdmin);

        viewDatabaseSelectionButton.addClickListener(e -> {
            getUI().getNavigator().navigateTo(DatabaseSelectionWindow.VIEW_NAME);
        });
        addNewEmploymentButton.addClickListener(e -> {
            // set  AddEmployment object
           panel.setContent(new AddingEmployment(employmentsPresenter));
            viewDatabaseSelectionButton.setVisible(true);
            viewlogButton.setVisible(true);
            viewEmploymentsButton.setVisible(true);
            addNewEmploymentButton.setVisible(false);
        });
        viewlogButton.addClickListener(e -> {
            try { //set LogGrid object
                panel.setContent( new LogGrid(employmentsPresenter));
                viewlogButton.setVisible(false);
                viewEmploymentsButton.setVisible(true);
                addNewEmploymentButton.setVisible(isAdmin);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        viewEmploymentsButton.addClickListener(e -> {
          // set EmploymentGrid
            panel.setContent( new EmploymentsGrid(employmentsPresenter));
            viewlogButton.setVisible(true);
            viewEmploymentsButton.setVisible(false);
            addNewEmploymentButton.setVisible(isAdmin);

        });

        ButtonsLayout.addComponents(viewDatabaseSelectionButton,viewlogButton,viewEmploymentsButton,addNewEmploymentButton);
        setSpacing(true);
        setMargin(true);
        addComponents( logoutHLayout,ButtonsLayout, panel);
        setComponentAlignment(logoutHLayout, Alignment.BOTTOM_RIGHT);
    }


     // method for generating error notification
    public void ShowErrorNotification(String msg){
        new Notification(msg, Notification.TYPE_ERROR_MESSAGE)
                .show(getUI().getPage());
        return;
    }
    public void showSuccessNotification(String msg){
        Notification.show(msg);
    }




    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // checking if the user is logged in and the database is selected before showing the window
         if((getUI().getSession().getAttribute("user")==null)){
             System.out.println("Not connected");
             getUI().getNavigator().navigateTo(LoginWindow.VIEW_NAME);
         } else if ((getUI().getSession().getAttribute("databaseName")==null))
         {
             System.out.println("Connected but no DB selected");
             getUI().getNavigator().navigateTo(DatabaseSelectionWindow.VIEW_NAME);
         } else {
             System.out.println("Connected & DB selected");
             try {

                 isAdmin= (boolean)getUI().getSession().getAttribute("isAdmin");
                 employmentsPresenter = new EmploymentsPresenter(this, isAdmin);
                 System.out.print(isAdmin);
             } catch (IOException e) {
                 e.printStackTrace();
             }
             addWindowComponents();

         }
    }
}
