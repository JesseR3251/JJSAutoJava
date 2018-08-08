import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Random;


public class JJSAuto extends JFrame {

	// components
	JPanel aPanel, bPanel,logoPanel,customerPanel,modelPanel,optionPanel,centerPanel,payPanel,bottomPanel, xPanel,yPanel;
	
	
	//customer
	JLabel phoneLabel, nameLabel, addressLabel, zipLabel, cityLabel, accountNumLabel;
	JTextField phoneTF, nameTF, addressTF, zipTF,cityTF,accountTF;
	
	//center
	//models
	JRadioButton S40B,S60B,S70B,S80B;
	ButtonGroup models;
	
	//options
	JCheckBox pkgAbox,pkgBbox,paintBox;
	
	// pay
	JLabel tradeLabel,paymentLabel,cashLabel,FinLabel;
	JTextField tradeTF,cashTF;
	JRadioButton cashBox,financeBox;
	ButtonGroup paymentType;
	
	//bottom
	JButton Contract, Exit;
	JLabel carCost, fees, tradeIn, discount, finance, grandTotal;
	JLabel carCost2, fees2, tradeIn2, discount2, finance2, grandTotal2;  //summary labels
	JLabel modelCost, optionCost, tradeInC, finC, taxC,titleFees, cashValue;
	JLabel modelCost2, optionCost2, tradeInC2, finC2, taxC2,titleFees2, cashValue2;
	
	//variables
	double totalCost=0.00, subtotal=0, basePrice=0, packageOption=0.00, tradeInValue=0.00, cashDiscount=0.00, financeCharge=0.00, tradeInPayment=0.00;
	private final double  SALES_TAX = 0.06;
	private final double FINANCE_INTEREST=0.07;
	private final int CASH_DISCOUNT=750;
	private final int TITLE=325;
	
	public JJSAuto() {
		aPanel=new JPanel();
		bPanel=new JPanel();
		logoPanel=new JPanel();
		customerPanel=new JPanel();
		modelPanel=new JPanel();
		optionPanel=new JPanel();
		centerPanel=new JPanel();
		payPanel=new JPanel();
		bottomPanel=new JPanel();
		xPanel=new JPanel();
		yPanel=new JPanel();
		
		
		// add picture to logo panel
		ImageIcon pic = new ImageIcon("/Users/johnacosta/eclipse-workspace/JJSAutoDealer/src/jjsAuto.png");
		logoPanel.add(new JLabel(pic));
		
		
		// configure customer panel
		customerPanel.setLayout(new GridLayout(6,2));
		customerPanel.setBorder(BorderFactory.createTitledBorder("Customer Information"));
		// instantiate the labels
		phoneLabel = new JLabel("Telephone: ");
		nameLabel= new JLabel("Name: ");
		addressLabel= new JLabel("Street Address: ");
		zipLabel= new JLabel("Zip code: ");
		cityLabel= new JLabel("City:");
		accountNumLabel= new JLabel("Account Number:");
		// instantiate the text fields
		phoneTF= new JTextField(10);
		nameTF= new JTextField(20);
		addressTF = new JTextField(20);
		zipTF= new JTextField(10);
		cityTF= new JTextField(10);
		accountTF = new JTextField(10);
		// add objects to panel
		customerPanel.add(phoneLabel);
		customerPanel.add(phoneTF);
		customerPanel.add(nameLabel);
		customerPanel.add(nameTF);
		customerPanel.add(addressLabel);
		customerPanel.add(addressTF);
		customerPanel.add(cityLabel);
		customerPanel.add(cityTF);
		customerPanel.add(zipLabel);
		customerPanel.add(zipTF);
		customerPanel.add(accountNumLabel);
		customerPanel.add(accountTF);
        
        //generate and set account number
        Random rand = new Random();
        int n=rand.nextInt(10000000)+10000;
        accountTF.setText(Integer.toString(n));
		
		// configure model panel
		modelPanel.setLayout(new GridLayout(4,1));
		modelPanel.setBorder(BorderFactory.createTitledBorder("Models"));
		cashBox=new JRadioButton("Cash ");
		financeBox=new JRadioButton("Financing 7%");
		paymentType = new ButtonGroup();
		paymentType.add(cashBox);
		paymentType.add(financeBox);
		models=new ButtonGroup();
		S40B=new JRadioButton("S40: $27700");
		S60B=new JRadioButton("S60: $32500");
		S70B=new JRadioButton("S70: $36000");
		S80B=new JRadioButton("S80: $44000");
		models.add(S40B);
		models.add(S60B);
		models.add(S70B);
		models.add(S80B);
		modelPanel.add(S40B);
		modelPanel.add(S60B);
		modelPanel.add(S70B);
		modelPanel.add(S80B);
		
		// configure option panel
		optionPanel.setLayout(new GridLayout(3,1));
		optionPanel.setBorder(BorderFactory.createTitledBorder("Options"));
		//JCheckBox 
		pkgAbox=new JCheckBox("Package A - $2200");
		pkgBbox=new JCheckBox("Package B -$3250 (only available for models S70 & S80)");
		//pkgBbox.setVisible(false);
		paintBox=new JCheckBox("Metallic Paint -$650");
		optionPanel.add(pkgAbox);
		optionPanel.add(pkgBbox);
		optionPanel.add(paintBox);
		
		
		//add option and model to centerPanel
		centerPanel.setLayout(new GridLayout(1,2));
		centerPanel.add(modelPanel);
		centerPanel.add(optionPanel);
		
		// configure pay panel
		payPanel.setLayout(new GridLayout(2,1));
		payPanel.setBorder(BorderFactory.createTitledBorder("Payments"));
		aPanel.setLayout(new GridLayout(2,2));
		bPanel.setLayout(new GridLayout(3,1));
		tradeLabel=new JLabel("Trade-In Value $:");
		tradeTF=new JTextField(8);
		tradeTF.setText("0.00");
		aPanel.add(tradeLabel);
		cashLabel=new JLabel("Cash Payment $");
		cashTF=new JTextField(8);
		cashTF.setText("0.00");
		aPanel.add(tradeTF);
		aPanel.add(cashLabel);
		aPanel.add(cashTF);
		
		paymentLabel=new JLabel("Payment Type:");

		bPanel.add(paymentLabel);
		bPanel.add(cashBox);
		bPanel.add(financeBox);
		
		
	
		payPanel.add(aPanel);
		payPanel.add(bPanel);
				
		
		// configure bottom panel
			// x panel
			xPanel.setBorder(BorderFactory.createTitledBorder("Summary"));
			xPanel.setLayout(new GridLayout(7,2));
			// left label
			carCost=new JLabel("Car Model + Options");
			fees=new JLabel("Title & Fees:");
			tradeIn=new JLabel("Trade-In value:");
			discount=new JLabel("Discount Amount:");
			finance=new JLabel("Finance 7%");
			grandTotal=new JLabel("Grand Total: $");
			//values to change
			carCost2=new JLabel("$0.00");
			fees2=new JLabel("+ $325.00");
			tradeIn2=new JLabel("$0.00");
			discount2=new JLabel("$0.00");
			finance2=new JLabel("$0.00");
			grandTotal2=new JLabel("$0.00"); 
			// add objects to panel
			xPanel.add(carCost);
			xPanel.add(carCost2);
			xPanel.add(fees);
			xPanel.add(fees2);
			xPanel.add(tradeIn);
			xPanel.add(tradeIn2);
			xPanel.add(discount);
			xPanel.add(discount2);
			xPanel.add(finance);
			xPanel.add(finance2);
			xPanel.add(grandTotal);
			xPanel.add(grandTotal2);
			// button
			Contract=new JButton("Checkout");
			xPanel.add(Contract);
			
			//y panel
			yPanel.setBorder(BorderFactory.createTitledBorder("Details"));
			yPanel.setLayout(new GridLayout(8,2));
			// left labels
			modelCost=new JLabel("Model Cost =");
			optionCost=new JLabel("Options =");
			tradeInC=new JLabel("Trade-In Cost = ");
			finC=new JLabel("Finance Charge =");
			taxC=new JLabel("Taxes =");
			titleFees=new JLabel("Titles & Fees =");
			cashValue=new JLabel("Cash Amount =");
			// values to change
			modelCost2=new JLabel("$0.00");
			optionCost2=new JLabel("$0.00");
			tradeInC2=new JLabel("$0.00");
			finC2=new JLabel("$0.00");
			taxC2=new JLabel("$0.00");
			titleFees2=new JLabel("$325.00");
			cashValue2=new JLabel("$0.00");
			yPanel.add(modelCost);
			yPanel.add(modelCost2);
			yPanel.add(optionCost);
			yPanel.add(optionCost2);
			yPanel.add(tradeInC);
			yPanel.add(tradeInC2);
			yPanel.add(finC);
			yPanel.add(finC2);
			yPanel.add(taxC);
			yPanel.add(taxC2);
			yPanel.add(titleFees);
			yPanel.add(titleFees2);
			yPanel.add(cashValue);
			yPanel.add(cashValue2);
			//button
			Exit=new JButton("EXIT");
			yPanel.add(Exit);
		
		
		bottomPanel.setLayout(new GridLayout(1,2));
		bottomPanel.add(xPanel);
		bottomPanel.add(yPanel);
		
		
		
		
				
		// main window
		this.setLayout(new GridLayout(5,1));
		add(logoPanel);
		add(customerPanel);
		add(centerPanel);
		add(payPanel);
		add(bottomPanel);
		this.setTitle("Welcome to JJS Auto");
		
		this.setSize(800, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
        
        S40B.addActionListener(new rbListener());
        S60B.addActionListener(new rbListener());
        S70B.addActionListener(new rbListener());
        S80B.addActionListener(new rbListener());
        Exit.addActionListener(new CloseListener());
        pkgAbox.addItemListener(new chBoxListener());
        pkgBbox.addItemListener(new chBoxListener());
        paintBox.addItemListener(new chBoxListener());
		S40B.setActionCommand("S40B");
		S60B.setActionCommand("S60B");
		S70B.setActionCommand("S70B");
		S80B.setActionCommand("S80B");
        
        //Intialize Trade in value listener
        tradeInValueListener(); 
        //Initialize Cash Amount listener
        cashValueListener();
     
        
        cashBox.addActionListener(new paymentChBoxListener());
        financeBox.addActionListener(new paymentChBoxListener());
        
        Contract.addActionListener(new checkoutListener());
        
        
	}
	
	/*
	 * Checkout button Listener cashValue2.getText()==totalCost
	 */
	private class checkoutListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(financeBox.isSelected()) {
				JOptionPane.showMessageDialog(null, "Congratulations on your purchase!\n Your total amount financed is: "+String.format("$%,.2f", totalCost));
			}
			else if(cashBox.isSelected()) {
				double cashPayment = Double.parseDouble(cashTF.getText());				
				if(cashPayment==totalCost)
					JOptionPane.showMessageDialog(null, "Congratulations on your purchase!\n Your total purchase price is: "+String.format("$%,.2f", totalCost));
				else if(cashPayment<totalCost)					
					JOptionPane.showMessageDialog(null, "Sorry insuffient funds! Please increase cash payment in the amount of: "+String.format("$%,.2f", totalCost-cashPayment)
					+"\n Or you may choose our financing option");
				
			}
		}
	}
	/*
	 * Exit button listener
	 */
	private class CloseListener implements ActionListener{
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        System.exit(0);
	    }
	}
	
    /*
     * Radio button listener for Model Selection
     */
    private class rbListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {        
	
        	if(e.getSource()==S40B) {
            	basePrice=27700;
            }

            if(e.getSource()==S60B) {
                basePrice=32500;
            }
            if(e.getSource()==S70B) {
                basePrice=36000;
            }
            if(e.getSource()==S80B) {
            	basePrice=44000; 
            	
            }           
            subtotal=basePrice;
            taxC2.setText(String.format("$%,.2f",(subtotal*SALES_TAX)));
            carCost2.setText(String.format("$%,.2f",subtotal+packageOption));
            totalCost=(subtotal*SALES_TAX)+subtotal+TITLE;
            grandTotal2.setText(String.format("$%,.2f",totalCost));
        }
        
    }
    /*
     * Checkbox listener for package option selection
     */
    private class chBoxListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource()==pkgAbox) {
				if(pkgAbox.isSelected())
					packageOption+=2200;
				else
					packageOption-=2200;
			}
			if(e.getSource()==pkgBbox && (models.getSelection().getActionCommand() == "S70B" || models.getSelection().getActionCommand() == "S80B")) {
				if(pkgBbox.isSelected())
					packageOption+=3250;
				else
					packageOption-=3250;
			}

			if(e.getSource()==paintBox) {
				if(paintBox.isSelected())
					packageOption+=650;
				else
					packageOption-=650;
			}
			
			subtotal=basePrice+packageOption;
			carCost2.setText(String.format("$%,.2f",subtotal));
			optionCost2.setText(String.format("$%,.2f",packageOption));
			totalCost=(subtotal*SALES_TAX)+subtotal+TITLE;
			taxC2.setText(String.format("$%,.2f",((subtotal + packageOption)*SALES_TAX)));
			grandTotal2.setText(String.format("$%,.2f",totalCost));
		}
    }
    
//Payments
    // Trades
    private static final int TIMER_VISIBLE = 1200;
    public void tradeInValueListener() {
	    tradeTF.addKeyListener(new KeyAdapter() {	    	
	    		public void keyReleased(KeyEvent e) {	
	    			tradeTF = (JTextField) e.getSource();
			       try {
			    	   	   tradeInPayment = Double.parseDouble(tradeTF.getText());
				    	   tradeInC2.setText(String.format("$%,.2f", tradeInPayment));
				    	   tradeIn2.setText(String.format("$%,.2f", tradeInPayment));
				    	   totalCost = ((subtotal-tradeInPayment)*SALES_TAX)+(subtotal-tradeInPayment)+TITLE;
				    	   grandTotal2.setText(String.format("$%,.2f",totalCost));
				    	   taxC2.setText(String.format("$%,.2f",(((subtotal - tradeInPayment)+ packageOption)*SALES_TAX)));
			       }
			       catch(NumberFormatException ex) {
			    	   tradeTF.setText("0.00");
			    	   JOptionPane pane = new JOptionPane("Invalid input, Please enter Dollar Amount $0.00", JOptionPane.ERROR_MESSAGE);
			    	   JDialog dialog = pane.createDialog(null,"Title");
			    	   dialog.setModal(false);
			    	   dialog.setVisible(true);
			    	   new Timer(TIMER_VISIBLE, new ActionListener() {
			    		   public void actionPerformed(ActionEvent e) {
			    			   dialog.setVisible(false);
			    		   }
			    	   }).start();
			    	   		
			    	   				    	   		
			       }
			       
	    			}

	    });
    }
    
    // Cash
    public void cashValueListener() {
	    cashTF.addKeyListener(new KeyAdapter() {	    	
	    		public void keyReleased(KeyEvent e) {	
	    			cashTF = (JTextField) e.getSource();
			       try {
				    	   double text ;
				    	   text= Double.parseDouble(cashTF.getText());
				    	   cashValue2.setText(String.format("$%,.2f", text));
			       }
			       catch(NumberFormatException ex) {
			    	   cashTF.setText("$0.00");
			    	   JOptionPane pane = new JOptionPane("Invalid input, Please enter Dollar Amount $0.00", JOptionPane.ERROR_MESSAGE);
			    	   JDialog dialog = pane.createDialog(null,"Title");
			    	   dialog.setModal(false);
			    	   dialog.setVisible(true);
			    	   new Timer(TIMER_VISIBLE, new ActionListener() {
			    		   public void actionPerformed(ActionEvent e) {
			    			   dialog.setVisible(false);
			    		   }
			    	   }).start();
			    	   		
			    	   				    	   		
			       }
			       
	    			}
	    });
    }
    
    //Payments continued
    private class paymentChBoxListener implements ActionListener{
    	
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==cashBox) {
					cashDiscount=CASH_DISCOUNT;
					financeCharge=0;
					discount2.setText("-$750.00");
					subtotal=basePrice+packageOption-cashDiscount;			
			    	totalCost = ((subtotal-tradeInPayment)*SALES_TAX)+(subtotal-tradeInPayment)+TITLE;
			    	grandTotal2.setText(String.format("$%,.2f",totalCost));
					
			}			
			else if(e.getSource()==financeBox) {		
					financeCharge=FINANCE_INTEREST;
					discount2.setText("$0.00");
					subtotal=basePrice+packageOption;
					totalCost = ((subtotal-tradeInPayment)*SALES_TAX)+(subtotal-tradeInPayment)+TITLE + ((subtotal-tradeInPayment)*financeCharge);
			    	grandTotal2.setText(String.format("$%,.2f",totalCost));
			}
			carCost2.setText(String.format("$%,.2f",subtotal));
			finC2.setText(String.format("$%,.2f",(subtotal*financeCharge)));
	    	finance2.setText(String.format("$%,.2f",(subtotal*financeCharge)));
		}
		
    }
    
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new JJSAuto();
		
		
	}

}
