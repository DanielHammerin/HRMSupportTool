package View.UserUI;

import Model.Entity.User;
import Presenter.UserPresenter;
import com.vaadin.data.Validator;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

;

/**
 * Created by Abeer on 5/19/2016.
 *
 */
public class UserInfoView extends Window  {
    private VerticalLayout content;

    private TextField firstName;
    private TextField lastName;
    private TextField userName;
    private TextField password;
    private TextField email;

    private CheckBox isAdmin;

    private HorizontalLayout userInfolayout1;
    private HorizontalLayout userInfolayout2;
    private HorizontalLayout userInfolayout3;
    private HorizontalLayout actions;

    private Button save;
    private Button cancel;
    private Button delete;

    protected Validator NameValidator,passwordValdiator;
    private User user;

   public UserInfoView (User user ,UserPresenter userPresenter){
       super("Edit User");
       init();
       addValidators();
       this.user=user;

       if (user.getId()!=-1) {
           BeanFieldGroup.bindFieldsUnbuffered(user, this);
           isAdmin.setValue(user.isAdmin());
       }
       save.addClickListener(e -> {
           if(validateUserParameter())
           {  userPresenter.updateUser(user.getId(),firstName.getValue(),lastName.getValue(),
                       userName.getValue(),password.getValue(),
                       email.getValue(),(boolean)isAdmin.getValue()); close();}
           else{
               new Notification("Enter required parameters", Notification.TYPE_ERROR_MESSAGE)
                       .show(getUI().getPage());
           }

       });
       delete.addClickListener(e -> {
           userPresenter.deleteUser(user.getUsername(),user.getPassword());
            close();
       });
   }


    public UserInfoView( UserPresenter userPresenter){
        super("New User");
        init();
        addValidators();
        delete.setVisible(false);
        save.addClickListener(e -> {
            if(validateUserParameter())
            {  userPresenter.addNewUser(firstName.getValue(),lastName.getValue(),
                        userName.getValue(),password.getValue(),
                        email.getValue(),isAdmin.getValue());close();}
            else{
                new Notification("Enter required parameters", Notification.TYPE_ERROR_MESSAGE)
                        .show(getUI().getPage());
            }
               });

    }
    public boolean validateUserParameter() {
        return (firstName.isValid()&&lastName.isValid()& userName.isValid()&&
                isValid(email.getValue())&&isValid(password.getValue()));
    }


    public boolean isValid(Object value) {

        if (value==null||value.toString().trim().isEmpty()){
            return false;}
        else
            return true;


    }
  private void addValidators(){
          NameValidator =new StringLengthValidator(
                  "Name must be 3-25 characters", 3, 25, true);
     passwordValdiator=new StringLengthValidator(
             "Name must be 6-10 characters", 6, 10, true);

      firstName.addValidator(NameValidator);
      lastName.addValidator(NameValidator);
      password.addValidator(passwordValdiator);

          firstName.setRequired(true);firstName.setImmediate(true);
          lastName.setRequired(true);lastName.setImmediate(true);
          userName.setRequired(true);userName.setImmediate(true);
          password.setRequired(true);password.setImmediate(true);
          email.setRequired(true);password.setImmediate(true);
      }

    private void init(){
        content = new VerticalLayout();

        firstName = new TextField("FirstName");
        lastName = new TextField("lastName");
        userName = new TextField("Username");
        password = new TextField("Password");
        email= new TextField("Email");

        isAdmin = new CheckBox("Admin");
        isAdmin.isEmpty();
        isAdmin.setImmediate(true);

        cancel= new Button("Cancel");
        save = new Button("Save", FontAwesome.EDIT);
        delete = new Button("Delete",FontAwesome.TRASH_O);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);


        userInfolayout1 = new HorizontalLayout(firstName,lastName);
        userInfolayout2 = new HorizontalLayout(userName,password);
        userInfolayout3 = new HorizontalLayout(email,isAdmin);
                actions = new HorizontalLayout(save, cancel,delete);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

        userInfolayout1.setSpacing(true);
        userInfolayout2.setSpacing(true);
        userInfolayout3.setSpacing(true);
        actions.setSpacing(true);

        setModal(true);
        center();
        setClosable(true);
        setResizable(true);
        setWidth("40%");
        setHeight("60%");

        cancel.addClickListener(e -> {
            close();
        });
        content.setSpacing(true);
        content.setMargin(true);
        content.addComponents(userInfolayout1,userInfolayout2,userInfolayout3,actions);
        setContent(content);
    }
  }

