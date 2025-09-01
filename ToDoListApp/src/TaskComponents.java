import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaskComponents extends JPanel implements ActionListener{
    private JCheckBox checkBox;
    private JTextPane taskField;
    private JButton deleteButton;

    public JTextPane getTaskField() {
        return taskField;
    }

    //this panel is used sp that we can make updates to task components
    private JPanel parentPanel;

    public TaskComponents(JPanel parentPanel){
        this.parentPanel = parentPanel;

        //task field
        taskField = new JTextPane();
        taskField.setPreferredSize(CommonConstants.TASKFIELD_SIZE);
        taskField.setContentType("text/html");

        //checkbox
        checkBox = new JCheckBox();
        checkBox.setPreferredSize(CommonConstants.CHECKBOX_SIZE);
        checkBox.addActionListener(this);

        //delete button
        deleteButton =  new JButton("X");
        deleteButton.setPreferredSize(CommonConstants.DELETE_BUTTON_SIZE);
        deleteButton.addActionListener(this);


        // add to this taskcomponent
        add(checkBox);
        add(taskField);
        add(deleteButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (checkBox.isSelected()) {
            //replaces all html tags to empty string to grab main text
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");
            taskField.setText("<html><s>" + taskText + "</s></html");
        } else if (!checkBox.isSelected()) {
            String taskText = taskField.getText().replaceAll("<[^>]*>", "");
            taskField.setText(taskText);
        }
        if (e.getActionCommand().equalsIgnoreCase("X")){
            //delete this component form parent panel
            parentPanel.remove(this);
            parentPanel.repaint();
            parentPanel.revalidate();
        }
    }
}
