import javax.swing.*;  
import java.sql.*;								//importing needed modules to use swing and awt
import java.awt.*;
import java.awt.event.*;

class stock extends JFrame implements ActionListener{        //entends jframe and impliments actionlistner 


	Statement stmt=null;                            //initilising statment and result set to be null with a sample query 
	ResultSet rs=null;
	String query="Select * from STOCK";

	JPanel p=new JPanel();                       //created a panel to position each GUI element 
	String typ[]={"tv","washing machine"};		//drop down option menu
	JButton add=new JButton("add"); 			//buttons 
	JButton search=new JButton("search");
	JButton delete=new JButton("delete");
	JButton update=new JButton("update");
	JButton clear=new JButton("clear");
	JButton quit=new JButton("quit");

	JTextField item_code=new JTextField();     //text fileds to enter user data
	JTextField item_name=new JTextField();
	JComboBox item_type=new JComboBox(typ);
	JTextField item_price=new JTextField();
	JTextField item_stock=new JTextField();
	JTextField option=new JTextField();

	JLabel code=new JLabel("item code:");      //lables to help user understand what to enter 
	JLabel name=new JLabel("item name:");
	JLabel type=new JLabel("item type");
	JLabel price=new JLabel("item price:");
	JLabel stock=new JLabel("item stock:");


	stock(){								//constuctor is invoked automatically when object comes into memory 
		setSize(500,500);					//setting starting windown size
		setVisible(true);					//making the frame visible 
		setTitle("Stock Maintenance Form"); //title is set 

		p.setLayout(null);					//so that there isnt any pre defined layouts
		add(p);								//add is used to add componenets to frame 

		p.add(add);							//p.add refers to adding to the pannel
		add.setBounds(350,10,100,50);		//setBonds are used to determin the positon og GUI elements (x,y,w,h)
		p.add(search);
		search.setBounds(350,90,100,50);
		p.add(delete);
		delete.setBounds(350,170,100,50);
		p.add(update);
		update.setBounds(350,250,100,50);
		p.add(clear);
		clear.setBounds(350,330,100,50);
		p.add(quit);
		quit.setBounds(350,410,100,50);

		p.add(code);
		code.setBounds(10,10,100,50);
		p.add(item_code);
		item_code.setBounds(90,25,100,20);

		p.add(name);
		name.setBounds(10,90,100,20);
		p.add(item_name);
		item_name.setBounds(90,85,100,20);

		p.add(type);
		type.setBounds(10,170,100,20);
		p.add(item_type);
		item_type.setBounds(90,170,100,20);
		

		p.add(item_price);
		item_price.setBounds(90,250,100,20);
		p.add(price);
		price.setBounds(10,250,100,20);
		


		p.add(item_stock);
		item_stock.setBounds(90,330,100,20);
		p.add(stock);
		stock.setBounds(10,330,100,20);

		p.add(option);
		option.setBounds(10,410,200,50);
		

		add.addActionListener(this);			//action listner is added to each 
		search.addActionListener(this);			//it determins what to do when that button is clicked 
		delete.addActionListener(this);
		update.addActionListener(this);
		clear.addActionListener(this);
		quit.addActionListener(this);
	}

	public void actionPerformed(ActionEvent a){			//action listner funtion

		String st=a.getActionCommand();
		switch(st.charAt(0)){					//switch case is used to understand which button is pressed by compaing the starting letters 
			case 'a':   						//create the sql db and table in advance 
						Connection conn = null; 	//jdbc connection protocols 
     
        				String driver   = "com.mysql.jdbc.Driver";  //do not change 
        				String db       = "stockdb";      //use any db that exists  
        				String url      = "jdbc:mysql://localhost:3306/" +db+"?autoReconnect=true&useSSL=true"; //do not change 
        				String user     = "root";		//usually root but prefered user 
        				String pass     = "";			//add your password if exiists 
   
        				System.out.println(url);
						int icode=Integer.parseInt(item_code.getText());
						String iname=item_name.getText();
						String itype=(String) item_type.getSelectedItem();
						float iprice=Float.parseFloat(item_price.getText());
						int avs=Integer.parseInt(item_stock.getText());
						try {
						Class.forName(driver).newInstance();
						conn = DriverManager.getConnection(url,user,pass);
						Statement stmt=conn.createStatement();
						String query="insert into stock values ("+icode +"," +"'"+iname+"'" +","+"'"+itype+"'" +","+iprice+","+ avs+");";
						System.out.println(query);
						stmt.executeUpdate(query);
						option.setText("Record added successfully");

						}
						catch(Exception e) {
						option.setText(e.getMessage());
						}
						break;
			case 's': 
						conn = null;
     
        				driver   = "com.mysql.jdbc.Driver";
        				db       = "stockdb";
        				url      = "jdbc:mysql://localhost:3306/" +db+"?autoReconnect=true&useSSL=true";
        				user     = "root";
        				pass     = "";
   
        				System.out.println(url);
						icode=Integer.parseInt(item_code.getText());
						try {
						Class.forName(driver).newInstance();
						conn = DriverManager.getConnection(url,user,pass);
						Statement stmt=conn.createStatement();
						query="Select * from stock where item_code ="+icode+";";
						ResultSet rs=stmt.executeQuery(query);

						if(rs.next()) {
						icode=rs.getInt("item_code");
						iname=rs.getString("item_name");
						itype=rs.getString("item_type");
						iprice=rs.getFloat("item_price");
						avs=rs.getInt("Available_stock");
						item_name.setText(""+icode); 
						item_name.setText(""+iname);
						item_type.setSelectedItem(itype); 
						item_price.setText(""+iprice);
						item_stock.setText(""+avs);
						conn.close(); stmt.close(); rs.close();
						}
						else
						option.setText("record cannot be found ");
						}
						catch(Exception e) {
						option.setText(e.getMessage());
						}
						break;
			case 'd': 
						conn = null;
     
        				driver   = "com.mysql.jdbc.Driver";
        				db       = "stockdb";
        				url      = "jdbc:mysql://localhost:3306/" +db+"?autoReconnect=true&useSSL=true";
        				user     = "root";
        				pass     = "";
   
        				System.out.println(url);
						icode=Integer.parseInt(item_code.getText());
						try {
						Class.forName(driver).newInstance();
						conn = DriverManager.getConnection(url,user,pass);
						Statement stmt=conn.createStatement();
						query="Delete from stock where item_code ="+icode+";";
						int No_Rows=stmt.executeUpdate(query);
						if(No_Rows==0)
						option.setText("Record does not exists...");
						else
						option.setText("Record is deleted...");
						}
						catch(Exception e) {
						option.setText(e.getMessage());
						}
						break;
			case 'u': 							//same steps only query is different 
						conn = null;
     
        				driver   = "com.mysql.jdbc.Driver";
        				db       = "stockdb";
        				url      = "jdbc:mysql://localhost:3306/" +db+"?autoReconnect=true&useSSL=true";
        				user     = "root";
        				pass     = "";
   
        				System.out.println(url);
						icode=Integer.parseInt(item_code.getText());
						iprice=Float.parseFloat(item_price.getText());
						try {
						Class.forName(driver).newInstance();
						conn = DriverManager.getConnection(url,user,pass);
						Statement stmt=conn.createStatement();
						query="update stock set item_price="+iprice+"where item_code ="+icode+";";
						option.setText("Record has been updated");
						stmt.executeUpdate(query);
						conn.close(); 
						stmt.close();
						}
						catch(Exception e) {
						option.setText(e.getMessage());
						}
						break;

			case 'c': 							//all fields are minimised to null
						item_code.setText("");
						item_name.setText("");
						item_type.setSelectedItem("LED TV");
						item_price.setText("");
						item_stock.setText("");
						option.setText("");
						break;
			case 'q': System.exit(0);break;			//system exists the program 

			default : System.exit(0);
		}
	}

	public static void main(String[] args) { //main funtion 
		stock f=new stock();				//frame object is created 

	}
}