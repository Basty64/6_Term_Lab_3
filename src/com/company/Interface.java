package com.company;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Interface extends JFrame {
    private JButton b = new JButton("Choose file");
    private JFileChooser bb = new JFileChooser("C:\\Программирование\\Java\\Лабы по Java 6 сем\\Лаба 3 (xml, json)\\Материалы");
    private JTree jTree = new JTree(new DefaultMutableTreeNode());

    public Interface() {

        super("Выбор файла");
        this.setBounds(300, 300, 300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = this.getContentPane();
        container.setLayout(new GridLayout(2, 2, 2, 2));

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Reactor> reactorList = new ArrayList<>();
                    int returnvalue = bb.showOpenDialog(Interface.this);
                    if (returnvalue == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = bb.getSelectedFile();
                        try {
                            Parser p = new Parser();
                            if (selectedFile.getName().endsWith(".xml")) {
                                reactorList = p.parsxml(selectedFile.getAbsolutePath());
                            } else if (selectedFile.getName().endsWith(".json")) {
                                reactorList = p.parsjson(selectedFile.getAbsolutePath());
                            } else if (selectedFile.getName().endsWith(".yaml")) {
                                reactorList = p.parsyaml(selectedFile.getAbsolutePath());
                            }
                            gettree(jTree, reactorList);


                            JOptionPane.showMessageDialog(null, "Done", "Import", JOptionPane.PLAIN_MESSAGE);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Ошибка", "Error", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                } catch (HeadlessException HeadlessException) { //ошибка
                    JOptionPane.showMessageDialog(null, "Not found", "Error", JOptionPane.PLAIN_MESSAGE);
                }

            }
        });
        container.add(b);
        JScrollPane jScrollPane = new JScrollPane(jTree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);// TODO: 07.04.2022
        container.add(jScrollPane);
    }

    public void gettree(JTree t, List<Reactor> reactors) {//дерево
        DefaultTreeModel tr = (DefaultTreeModel) t.getModel();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        DefaultMutableTreeNode sourse = new DefaultMutableTreeNode(reactors.get(0).getSourse());//взяли нулевой реактор
        root.add(sourse);
        for (Reactor reactor : reactors) {     //цикл foreach
            DefaultMutableTreeNode y = new DefaultMutableTreeNode(reactor.getClasss()); //создаем место, куда добавляем все данные о реакторе
            sourse.add(y); //добавление элемента след уровня
            List<String> str = new ArrayList<>();
            str.add("Class: " + reactor.getClasss());
            str.add("Burnup: " + reactor.getBurnup());
            str.add("Kpd: " + reactor.getKpd());
            str.add("Enrichment: " + reactor.getEnrichment());
            str.add("Termal capacity: " + reactor.getTermal_capacity());
            str.add("Electrical capacity: " + reactor.getElectrical_capacity());
            str.add("Life time: " + reactor.getLife_time());
            str.add("First load: " + reactor.getFirst_load());
            for (String str1 : str) {
                y.add(new DefaultMutableTreeNode(str1));//еще элементы на уровень глубже
            }
        }
        tr.setRoot(root);
        tr.reload();//показывает заполнение JTree, обновление


    }
}