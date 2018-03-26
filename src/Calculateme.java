import com.sun.org.apache.xpath.internal.operations.Operation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculateme implements ActionListener{
    JFrame guiFrame;
    JPanel button;
    JTextField numbercalc;
    int calcoperation=0;
    int currentcalc;

    public static void main(String[] args) {
        /*event que invoke later enables you to do a processing after all GUI processes are finished*/
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculateme();
            }
        });
    }
    public Calculateme(){
        guiFrame=new JFrame();
        //makes sure program exits when frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("My Calculator");
        guiFrame.setSize(200,300);
        //center JFrame
        guiFrame.setLocationRelativeTo(null);
        numbercalc=new JTextField();
        numbercalc.setHorizontalAlignment(JTextField.RIGHT);
        numbercalc.setEditable(false);
        guiFrame.add(numbercalc,BorderLayout.NORTH);
        button=new JPanel();
        //making the grid for the calc
        button.setLayout(new GridLayout(4,3));
        guiFrame.add(button,BorderLayout.CENTER);
        //We add buttons
        for (int i=1;i<10;i++){
            addButton(button,String.valueOf(i));
        }
        JButton addButton=new JButton("+");
        addButton.setActionCommand("+");
        OperatorAction subAction=new OperatorAction(1);
        addButton.addActionListener(subAction);

        JButton subButton=new JButton("-");
        subButton.setActionCommand("-");
        OperatorAction addAction=new OperatorAction(2);
        subButton.addActionListener(addAction);

        JButton equalsButton=new JButton("=");
        equalsButton.setActionCommand("=");
        equalsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                if (!numbercalc.getText().isEmpty()){
                    int number=Integer.parseInt(numbercalc.getText());
                    if (calcoperation==1){
                        int calculate= currentcalc+number;
                        numbercalc.setText(Integer.toString(calculate));
                    }
                    else if(calcoperation==2){
                        int calculate=currentcalc-number;
                        numbercalc.setText(Integer.toString(calculate));
                    }
                }
            }
        });
        button.add(addButton);
        button.add(subButton);
        button.add(equalsButton);
        guiFrame.setVisible(true);

    }
    private void addButton(Container parent,String name){
        JButton but=new JButton(name);
        but.setActionCommand(name);
        but.addActionListener(this);
        parent.add(but);

    }
    public void actionPerformed(ActionEvent event){
        //grt action command text from the button
        String action=event.getActionCommand();
        //set text using the action command text
        numbercalc.setText(action);

    }
    private class OperatorAction implements ActionListener{
        private int operator;
        public OperatorAction(int operation){
            operator=operation;
        }
        public void actionPerformed(ActionEvent event){
            currentcalc=Integer.parseInt(numbercalc.getText());
            calcoperation=operator;
        }
    }

}
