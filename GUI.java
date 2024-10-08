
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;

/*
  TODO:
  1) Change credentials for your own team's database
  2) Change SQL command to a relevant query that retrieves a small amount of data
  3) Create a JTextArea object using the queried data
  4) Add the new object to the JPanel p
 */
public class GUI extends JFrame implements ActionListener {

    static JFrame f;

    public static void main(String[] args) {
        //Building the connection
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            //TODO STEP 1
            conn = DriverManager.getConnection("jdbc:postgresql://csce-315-db.engr.tamu.edu/DBNAME",
                    "username", "password");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        JOptionPane.showMessageDialog(null, "Opened database successfully");

        String data = "";
        try {
            //create a statement object
            Statement stmt = conn.createStatement();
            //create an SQL statement
            //TODO Step 2
            String sqlStatement = "SQL QUERY HERE";
            //send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);
            while (result.next()) {
                // TODO specify the column name
                data += result.getString("COLUMN NAME HERE") + "\n";
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        // create a new frame
        f = new JFrame("DB GUI");

        // create a object
        GUI s = new GUI();

        // create a panel
        JPanel p = new JPanel();

        JButton b = new JButton("Close");

        // add actionlistener to button
        b.addActionListener(s);

        //TODO Create a JTextArea object using the queried data

        //TODO Add JTextArea object to JPanel p
        
        // add button to panel
        p.add(b);

        // add panel to frame
        f.add(p);

        // set the size of frame
        f.setSize(400, 400);

        f.show();

        //closing the connection
        try {
            conn.close();
            JOptionPane.showMessageDialog(null, "Connection Closed.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Connection NOT Closed.");
        }
    }

    // if button is pressed
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Close")) {
            f.dispose();
        }
    }
}
