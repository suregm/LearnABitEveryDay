package learn.TestGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by sure GM on 2016/6/14 7:10.
 */
public class TestEvent extends JFrame implements ActionListener {

    ArrayList<JCheckBox> cbs = new ArrayList<>();
    JCheckBox cbAll;
    JCheckBox cb;

    public TestEvent() {
        super("TestJScrollPane");
        this.setLayout(null);
        this.setBounds(0, 0, 400, 400);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 300, 300);


        JPanel panel = new JPanel();
//        panel.setPreferredSize(new Dimension(200,100));//主要是这句代码，设置panel的首选大小，同时保证宽高大于JScrollPane的宽高，这样下面的JScrollPane才会出现滚动条
        panel.setLayout(new GridLayout(18,1, 5, 5));

        cbAll = new JCheckBox("All");
        cbAll.setSelected(true);
        cbAll.setVisible(true);
        panel.add(cbAll);
        cbs.add(cbAll);

        for(int i = 0; i < 18; i++) {
            cb = new JCheckBox("dddd" + i);
            cb.setSelected(true);
            cb.setVisible(true);
            panel.add(cb);
            cbs.add(cb);
            cb.addActionListener(this);
        }

        scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(10, 10, 300, 300);
        this.getContentPane().add(scrollPane);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cbAll.addActionListener(this);
    }

    public static void main(String[] args) {
        new TestEvent();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cbAll) {
            System.out.println(cbs.size());

            for(JCheckBox cb : cbs) {
                if(cbAll.isSelected()) {
                    cb.setSelected(true);
                } else {
                    cb.setSelected(false);
                }
            }

        } else if(!((JCheckBox)e.getSource()).getText().equals("All")) {
            boolean allSelect = true;

            for(JCheckBox cb : cbs) {
                if(!cb.getText().equals("All")) {
                    if(!cb.isSelected()) {
                        cbAll.setSelected(false);
                        allSelect = allSelect && false;
                    }
                }
            }

            cbAll.setSelected(allSelect);
        }
    }
}

