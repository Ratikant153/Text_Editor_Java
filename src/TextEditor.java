import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements  ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;

    JMenuItem newFile,saveFile,openFile;
    JMenuItem cut,copy,paste,selectAll,close;
    JTextArea textArea;

    TextEditor()
    {
        // initilalise the frame
        frame=new JFrame();

        // initialise the manu bar
        menuBar=new JMenuBar();

        // Initialise the menu
        file=new JMenu("File");
        edit=new JMenu("Edit");
        // initialise the textArea
        textArea=new JTextArea();
       //  textArea.setBackground(Color.lightGray);



        // Initialise the menu item
        // initialise the file menu item
        newFile =new JMenuItem("New Window");
        saveFile=new JMenuItem("Save File");
        openFile=new JMenuItem("Open file");

        // edit menu items
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close Window");

        // add Action Listener to file menu item.
        newFile.addActionListener(this);
        saveFile.addActionListener(this);
        openFile.addActionListener(this);
        // add action listener to edit menu item
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);


        // adding menu item to file menu
        file.add(newFile);
        file.add(saveFile);
        file.add(openFile);
        // adding menu item to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // Add menu to menu bar
        menuBar.add(file);
        menuBar.add(edit);

        //set manuBar to our frame
        frame.setJMenuBar(menuBar);



        // create content pane
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(10,10,20,10));
        panel.setLayout(new BorderLayout(0,0));

        // add the text area to panel
        panel.add(textArea,BorderLayout.CENTER);


        //creata scroll panel
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        //add scroll paeneto the panel
        panel.add(scrollPane);

        // add panel to frame
        frame.add(panel);

        // set dimension of frame
        frame.setBounds(200,100,700,500);
        // set frame to be visible
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        // if cut menu action is clicked
        if(actionEvent.getSource()==cut){
                textArea.cut();
        }
        // if copy menu action is clicked
        if(actionEvent.getSource()==copy){
                textArea.copy();
        }
        // if paste action is clicked
        if(actionEvent.getSource()==paste){
                textArea.paste();
        }
        // if select All menu action is clicked
        if(actionEvent.getSource()==selectAll){
                textArea.selectAll();
        }
        // if close menu action is clicked
        if(actionEvent.getSource()==close){
                System.exit(0);
        }
        // if newFile menu action is clicked
        if(actionEvent.getSource()==newFile){
            TextEditor newWindow=new TextEditor();
        }
        // if openFile menu action is clicked
        if(actionEvent.getSource()==openFile){
            //initialise a file chooser
            JFileChooser fileChooser=new JFileChooser("F:");
            //getting choose option in file chooser
            int chooseOption=fileChooser.showOpenDialog(null);
            // if choose option is the approve option
            if(chooseOption==JFileChooser.APPROVE_OPTION)
            {
                //Getting selected file from the file chooser
                File file=fileChooser.getSelectedFile();
                //getting path of the selected file
                String filePath=file.getPath();
                try{
                    //initialise file reader
                    FileReader fileReader=new FileReader(filePath);
                    //initialise the buffer reader
                    BufferedReader bufferedReader=new BufferedReader(fileReader);
                    //intemediate for current line ; output for complete content of file
                    String intermediate="",output="";
                    //read line by line
                    while((intermediate=bufferedReader.readLine())!=null)
                    {
                        output+=intermediate+"\n";

                    }
                    //set text area  with the content of the file
                    textArea.setText(output);
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }

        }
        // if saveFile menu action is clicked
        if(actionEvent.getSource()==saveFile){
            // initialise file chooser
            JFileChooser fileChooser=new JFileChooser("F:");
            //get choose option from file chooser
            int chooseOption =fileChooser.showSaveDialog(null);
            //if choose option is the approve option
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    // intialise the buffer writer
                    BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file));
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();


                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }

        }


    }
    public static void main(String[] args) {
            TextEditor textEditor=new TextEditor();

    }
}