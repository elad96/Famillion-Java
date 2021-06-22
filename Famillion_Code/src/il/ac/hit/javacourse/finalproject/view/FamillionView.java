package il.ac.hit.javacourse.finalproject.view;

import il.ac.hit.javacourse.finalproject.model.Category;
import il.ac.hit.javacourse.finalproject.model.Cost;
import il.ac.hit.javacourse.finalproject.model.Currency;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import il.ac.hit.javacourse.finalproject.viewmodel.IFammillionViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

public class FamillionView implements IFamillionView {

    /** FammillionView fields */
    private ApplicationFrame applicationFrame;
    private IFammillionViewModel famillionViewModel;

    /** FamillionView constructor which initializes the UI and calls start method to link and set all the components together*/
    public FamillionView(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FamillionView.this.applicationFrame = new ApplicationFrame();
                FamillionView.this.applicationFrame.start();
            }
        });
    }

    /**
     * Function Description: Displays pie chart of costs arraylist
     * @param costs the costs to be displayed
     * */
    @Override
    public void displayPieChart(List<Cost> costs) {
        // initialize the dataset and add each and every given cost
        DefaultPieDataset pieDataset = new DefaultPieDataset();
        for(Cost cost : costs){
            pieDataset.setValue(cost.getDescription(),cost.getSum());
        }
        // create the chart with desired attributes and link the early created dataset
        JFreeChart chart = ChartFactory.createPieChart3D("Costs Pie Chart", pieDataset, true, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("Narkisim", Font.BOLD, 16));
        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{0} : {1}",
                new DecimalFormat("0"),
                new DecimalFormat("0%"));
        plot.setLabelGenerator(gen);
        ChartPanel chartPanel = new ChartPanel(chart);
        if(SwingUtilities.isEventDispatchThread()){
            FamillionView.this.applicationFrame.addNewCategory(chartPanel);
        }
        else{
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FamillionView.this.applicationFrame.addNewCategory(chartPanel);
                }
            });
        }


    }

    /**
     * Function Description: Displays bar chart of costs arraylist
     * @param costs the costs to be displayed
     * */
    @Override
    public void displayBarChart(List<Cost> costs) {
        // initialize the dataset and add each and every given cost
        DefaultCategoryDataset barChartDataset = new DefaultCategoryDataset();
        for(Cost cost : costs){
            barChartDataset.addValue(cost.getSum(), cost.getDescription(),"");
        }
        // create the chart with desired attributes and link the early created dataset
        JFreeChart chart = ChartFactory.createBarChart("Report", "Dates color index", "Cost",barChartDataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        if(SwingUtilities.isEventDispatchThread()){
            FamillionView.this.applicationFrame.addNewCategory(chartPanel);
        }
        else{
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FamillionView.this.applicationFrame.addNewCategory(chartPanel);
                }
            });
        }
    }

    /**
     *  Function Description: sets the data table with values of costs
     * @param costs the costs to be displayed
     * */
    @Override
    public void setTable(List<Cost> costs) {
        FamillionView.this.applicationFrame.setTable(costs);
    }

    /** Function Description: Displays feedback message to user
     * @param messageToShow the message to be shown
     * @param status status number
     */
    @Override
    public void showFeedbackMessage(String messageToShow, int status) {

        if(SwingUtilities.isEventDispatchThread()){
            FamillionView.this.applicationFrame.showFeedBackMessage(messageToShow,status);        }
        else{
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FamillionView.this.applicationFrame.showFeedBackMessage(messageToShow,status);                }
            });
        }
    }

    /** Function Description: Initializes the combo boxes with category arraylist
     * @param returnedCategory the categories to be set into the combo boxes
     */
    @Override
    public void initializeCategories(List<Category> returnedCategory) {
        if(SwingUtilities.isEventDispatchThread()){
            FamillionView.this.applicationFrame.populateCategoryComboBoxes(returnedCategory);
        }
        else{
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FamillionView.this.applicationFrame.populateCategoryComboBoxes(returnedCategory);
                }
            });
        }
    }

    /** Function Description: Initializes the combo boxes with category arraylist
     * @param returnedCurrency the categories to be set into the combo boxes
     */
    @Override
    public void initializeCurrencies(List<Currency> returnedCurrency) {
        if(SwingUtilities.isEventDispatchThread()){
            FamillionView.this.applicationFrame.populateCurrencyComboBoxes(returnedCurrency);
        }
        else{
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FamillionView.this.applicationFrame.populateCurrencyComboBoxes(returnedCurrency);
                }
            });
        }
    }

    /**
     *  Function Description: Sets the viewmodel interface attached to the view
     * @param vm The viewmodel to be set
     * */
    @Override
    public void setViewModel(IFammillionViewModel vm) {
        this.famillionViewModel = vm;
        famillionViewModel.getCategories();
        famillionViewModel.getCurrencies();
    }

    /**
     *  Function Description: Adds a new cost to the model
     * @param costToAdd the cost to be added to the model
     * */
    @Override
    public void addNewCost(Cost costToAdd) {
        if(SwingUtilities.isEventDispatchThread()){
            this.applicationFrame.addCostToTable(costToAdd);
        }
        else{
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    FamillionView.this.applicationFrame.addCostToTable(costToAdd);
                }
            });
        }
    }

    /**
     *  Function Description: Adds a new category to the model
     * @param categoryToAdd the category to be added to the model
     * */
    @Override
    public void addCategory(Category categoryToAdd) {
            if(SwingUtilities.isEventDispatchThread()){
                applicationFrame.addNewCategory(categoryToAdd);
            }
            else{
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        applicationFrame.addNewCategory(categoryToAdd);
                    }
                });
            }
    }

    /**
     * Class description: Application frame as an inner class to the view instance
     *     Each and every custom frame being instantiated by the main frame is an inner class to the ApplicationFrame
     *     Each and every custom frame has start() method already declared and used
     *     That option is for the user of each and every panel separately to support multi-threaded load scenario as learned in class
     * */
    public class ApplicationFrame extends JFrame {
        /** ApplicationFrame color Palette for ease of access*/
        private final Color layeredPanelColor = new Color(230, 230, 230);
        private final Color colorFeedbackBackground = new Color(200, 200, 200);
        private final Color colorLineBorder = new Color(14, 4, 4);
        private final Color colorLblFamillion = new Color(45, 72, 248, 164);
        private final Color colorGoodFeedback = new Color(25, 109, 11, 82);
        private final Color colorBadFeedback = new Color(212, 15, 15, 82);
        private final Color colorNorthPanel = new Color(212, 190, 95, 37);
        private final Color colorTopPanel = new Color(192, 192, 226, 153);
        /** ApplicationFrame panels */
        private AddCostPanel addCostPanel;
        private AddCategoryPanel addCategoryPanel;
        private ManageCategoriesPanel manageCategoriesPanel;
        private ReportByCategoryPanel reportByCategoryPanel;
        private ReportByDatePanel reportByDatePanel;
        /** Swing components*/
        private JTextField textFieldFeedback;
        private JPanel panelTop;
        private JPanel panelContent;
        private JPanel panelReportContent;
        private JPanel panelTableData;
        private JPanel panelChart;
        private JPanel panelCostAndCategoryContent;
        private JPanel panelRight;
        private JLayeredPane layeredPane;
        private JButton btnReport;
        private JButton btnCostAndCategories;
        private JLabel lblFamillion;
        private FlowLayout flowLayoutPanelTop;
        private FlowLayout flowLayoutPanelReportContent;
        private FlowLayout flowLayoutCostAndCategory;
        private GridLayout gridLayoutRightPanel;
        private JScrollPane scrollPaneTable;
        private JTable tableData;

        /**
         *  Function Description: ApplicationFrame constructor
         * */
        public ApplicationFrame() {
            // initializing top panel components
            BorderLayout contentPaneLayout = new BorderLayout();
            panelContent = new JPanel(contentPaneLayout);
            flowLayoutPanelTop = new FlowLayout(0, 35, 0);
            panelTop = new JPanel();
            btnReport = new JButton("Report");
            btnCostAndCategories = new JButton("Cost & Categories");
            lblFamillion = new JLabel("F  a  m  i  l  l  i  o  n");
            // initializing the panel which can be switched from report panel to cost & categories panel
            layeredPane = new JLayeredPane();
            // initialing the report related components
            flowLayoutPanelReportContent = new FlowLayout(0, 75, 25);
            panelReportContent = new JPanel();
            reportByCategoryPanel = new ReportByCategoryPanel();
            reportByDatePanel = new ReportByDatePanel();
            panelTableData = new JPanel();
            panelChart = new JPanel();
            // initializing the cost & categories related components
            flowLayoutCostAndCategory = new FlowLayout(0, 20, 150);
            panelCostAndCategoryContent = new JPanel();
            addCostPanel = new AddCostPanel();
            gridLayoutRightPanel = new GridLayout(2, 1, 0, 40);
            panelRight = new JPanel();
            addCategoryPanel = new AddCategoryPanel();
            manageCategoriesPanel = new ManageCategoriesPanel();
            textFieldFeedback = new JTextField();
            scrollPaneTable = new JScrollPane();
            tableData = new JTable();
        }

        /** Frame start method for linking and setting all the components*/
        public void start() {
            // call each custom panel start method to connect and customizing it
            addCostPanel.start();
            addCategoryPanel.start();
            manageCategoriesPanel.start();
            reportByCategoryPanel.start();
            reportByDatePanel.start();
            // customize the main frame
            setApplicationFrame();
            setTopPanel();
            setLayeredPanel();
            setFeedbackPanel();
            this.setVisible(true);
        }

        /** Setting the feedback panel for operation callbacks */
        private void setFeedbackPanel() {
            textFieldFeedback.setFont(new Font("Narkisim", Font.BOLD, 30));
            textFieldFeedback.setHorizontalAlignment(SwingConstants.CENTER);
            textFieldFeedback.setPreferredSize(new Dimension(1300, 75));
            textFieldFeedback.setBackground(colorFeedbackBackground);
            textFieldFeedback.setEditable(false);
            panelContent.add(textFieldFeedback, BorderLayout.SOUTH);
        }

        /** Setting and linking ApplicationFrame Components */
        private void setApplicationFrame() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(0, 0, 1300, 1050);
            panelContent.setBorder(BorderFactory.createLineBorder(colorLineBorder, 2));
            this.add(panelContent);

        }

        /** Setting the layered pane which is the main client area */
        private void setLayeredPanel() {
            layeredPane.setBounds(0, 200, 1200, 850);
            layeredPane.setLayout(new CardLayout(0, 0));
            setReportPanel();
            setCostAndCategoryPanel();
            panelContent.add(layeredPane, BorderLayout.CENTER);
        }

        /** Setting and linking the CostAndCategories Components */
        private void setCostAndCategoryPanel() {
            panelCostAndCategoryContent.setLayout(flowLayoutCostAndCategory);
            panelCostAndCategoryContent.setBounds(0, 200, 1200, 850);
            panelCostAndCategoryContent.setBackground(layeredPanelColor);
            panelCostAndCategoryContent.add(addCostPanel);
            panelRight.setLayout(gridLayoutRightPanel);
            panelRight.add(addCategoryPanel);
            panelRight.add(manageCategoriesPanel);
            panelCostAndCategoryContent.add(panelRight);
            layeredPane.add(panelCostAndCategoryContent);
        }

        /** Setting and linking the ReportPanel Components */
        private void setReportPanel() {
            panelReportContent.setLayout(flowLayoutPanelReportContent);
            panelReportContent.setBounds(0, 200, 1600, 850);
            panelReportContent.setBackground(layeredPanelColor);
            panelReportContent.add(reportByCategoryPanel);
            panelReportContent.add(reportByDatePanel);
            panelTableData.setLayout(new BorderLayout());
            panelTableData.add(scrollPaneTable);
            tableData.setFont(new Font("Narkisim", Font.PLAIN, 15));
            tableData.setRowHeight(30);
            tableData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            scrollPaneTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPaneTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPaneTable.setViewportView(tableData);
            panelTableData.setPreferredSize(new Dimension(500, 500));
            panelReportContent.add(panelTableData,BorderLayout.CENTER);
            panelChart.setPreferredSize(new Dimension(500, 500));
            panelChart.setLayout(new BorderLayout());
            panelReportContent.add(panelChart);
            layeredPane.add(panelReportContent);
        }

        /** Method responsible for populating all the category combo boxes on load
         * @param returnedCategory: all the categories that has been returned from the model */
        private void populateCategoryComboBoxes(List<Category> returnedCategory) {
                clearAllComboBoxes();
                for(Category cat : returnedCategory){
                    FamillionView.this.applicationFrame.reportByCategoryPanel.comboBoxCategory.addItem(cat);
                    FamillionView.this.applicationFrame.addCostPanel.comboBoxCategory.addItem(cat);
                    FamillionView.this.applicationFrame.manageCategoriesPanel.comboBoxCategoryName.addItem(cat);
                }
        }

        /** Method responsible for populating the currency combo box on load
         * @param returnedCurrencies: all the currencies that has been returned from the model */
        private void populateCurrencyComboBoxes(List<Currency> returnedCurrencies) {
            for(Currency currency : returnedCurrencies){
                FamillionView.this.applicationFrame.addCostPanel.comboBoxCurrency.addItem(currency);
            }
        }

        /** Method responsible for clearing all the category combo boxes on load */
        private void clearAllComboBoxes() {
            if(FamillionView.this.applicationFrame.reportByCategoryPanel.comboBoxCategory.getItemCount() > 0){
                FamillionView.this.applicationFrame.reportByCategoryPanel.comboBoxCategory.removeAllItems();
                FamillionView.this.applicationFrame.addCostPanel.comboBoxCategory.removeAllItems();
                FamillionView.this.applicationFrame.manageCategoriesPanel.comboBoxCategoryName.removeAllItems();
            }

        }

        /** Setting the top panel that acts as the client navigation menu */
        private void setTopPanel() {
            panelTop.setLayout(flowLayoutPanelTop);
            panelTop.setBackground(colorTopPanel);
            setBtnReport();
            setBtnCostAndCategory();
            setLblFamillion();
            panelTop.setBounds(0, 0, 1200, 200);
            panelTop.add(btnReport);
            panelTop.add(btnCostAndCategories);
            panelTop.add(lblFamillion);
            panelContent.add(panelTop, BorderLayout.NORTH);
        }

        /** Set the famillion logo label*/
        private void setLblFamillion() {
            lblFamillion.setFont(new Font("Papyrus", Font.BOLD, 50));
            lblFamillion.setBounds(1000, 0, 500, 100);
            lblFamillion.setForeground(colorLblFamillion);
        }

        /** Set navigation button for Cost & Category panel */
        private void setBtnCostAndCategory() {
            btnCostAndCategories.setFont(new Font("Narkisim", Font.BOLD, 30));
            btnCostAndCategories.setPreferredSize(new Dimension(300, 100));
            // show the cost and category panel when the button is pressed
            btnCostAndCategories.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        layeredPane.removeAll();
                        layeredPane.add(panelCostAndCategoryContent);
                        layeredPane.revalidate();
                }
            });
        }

        /** Set navigation button for Report panel */
        private void setBtnReport() {
            btnReport.setFont(new Font("Narkisim", Font.BOLD, 30));
            btnReport.setPreferredSize(new Dimension(200, 100));
            // show the report panel when the button is pressed
            btnReport.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    layeredPane.removeAll();
                    layeredPane.add(panelReportContent);
                    layeredPane.revalidate();
                }
            });
        }

        /** Method in charge of populating the table on the report panel
         * @param returnedCosts - returned client costs from the model */
        public void setTable(List<Cost> returnedCosts) {
            // create the titles for the tables
            DefaultTableModel tm = new DefaultTableModel(new String[]{"Category","Sum","Currency","Description","Date"},0);
            // add each cost to the table model
            for(Cost cost : returnedCosts){
                tm.addRow(new Object[]{cost.getCategory(),cost.getSum(),cost.getCurrency(),cost.getDescription(),cost.getDate()});
            }
            // link the model and the table
            tableData.setModel(tm);
        }

        /** Show feedback message from model operation as feedback to the user
         * @param messageToShow - message that has been returned form the view model
         * @param status: 0 - for good feedback, 1 - for bad feedback
         */
        public void showFeedBackMessage(String messageToShow, int status) {
            // check whether the calling thread is the main thread
            if(SwingUtilities.isEventDispatchThread()){
                determineFeedbackColor(status);
                textFieldFeedback.setText(messageToShow);
            }
            else{
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        determineFeedbackColor(status);
                        textFieldFeedback.setText(messageToShow);
                    }
                });
            }
        }

        /** Set the feedback font color -
         * @param status : 0 - for good feedback, sets font color to green, 1 - for bad feedback, sets font color to red  */
        private void determineFeedbackColor(int status) {
            switch (status){
                case 0:
                    textFieldFeedback.setForeground(colorGoodFeedback);
                    break;
                case 1:
                    textFieldFeedback.setForeground(colorBadFeedback);
                    break;
            }
        }

        /** Adds newly added cost to the data table
         * @param costToAdd: newly made client cost */
        public void addCostToTable(Cost costToAdd) {
             DefaultTableModel tm = (DefaultTableModel) tableData.getModel();
             tm.addRow(new Object[]{costToAdd.getCategory(),costToAdd.getSum(),costToAdd.getCurrency(),costToAdd.getDescription(),costToAdd.getDate()});
        }

        /** Adds newly added category to the application category combo boxes
         * @param categoryToAdd: newly added category by the client */
        public void addNewCategory(Category categoryToAdd) {
            applicationFrame.reportByCategoryPanel.comboBoxCategory.addItem(categoryToAdd);
            applicationFrame.addCostPanel.comboBoxCategory.addItem(categoryToAdd);
            applicationFrame.manageCategoriesPanel.comboBoxCategoryName.addItem(categoryToAdd);
        }

        /** Adds newly generated chart to the chart panel
         * @param chartPanel: newly added category by the client */
        public void addNewCategory(ChartPanel chartPanel) {
            // repopulate the chart panel
            FamillionView.this.applicationFrame.panelChart.removeAll();
            FamillionView.this.applicationFrame.panelChart.add(chartPanel);
            FamillionView.this.applicationFrame.panelChart.updateUI();
        }

        /** Report by date panel*/
        public class ReportByDatePanel extends JPanel {

            /** Swing components*/
            private JLabel lblReportByDate;
            private JLabel lblFrom;
            private JLabel lbllTo;
            private JTextField textFieldFrom;
            private JTextField textFieldTo;
            private JButton btnShowByDate;
            private JPanel panelNorth;
            private JPanel panelCenter;
            private JPanel panelCenterTop;
            private JPanel panelCenterBottom;
            private JPanel panelSouth;
            private GridLayout gridLayoutCenterPanel;

            /** ReportByDatePanel constructor with all the swing components initilization*/
            public ReportByDatePanel() {
                btnShowByDate = new JButton("Show");
                panelNorth = new JPanel();
                gridLayoutCenterPanel = new GridLayout(2, 1);
                panelCenter = new JPanel(gridLayoutCenterPanel);
                panelCenterTop = new JPanel(new FlowLayout());
                panelCenterBottom = new JPanel(new FlowLayout());
                panelSouth = new JPanel();
                textFieldFrom = new JTextField("yyyy-mm-dd");
                textFieldTo = new JTextField("yyyy-mm-dd");
                lbllTo = new JLabel("To:");
                lblFrom = new JLabel("From:");
                lblReportByDate = new JLabel("Report by Date");
            }

            /** Panel start method for linking and setting all the components*/
            private void start() {
                setReportByDateLabel();
                setLabelFrom();
                setLabelTo();
                setTextFields();
                setButtonShowByDate();
                locateComponentesAndSetMainPanel();
            }

            /** Set JButton btnShowByDate*/
            private void setButtonShowByDate() {
                btnShowByDate.setFont(new Font("Narkisim", Font.BOLD, 30));
                btnShowByDate.setPreferredSize(new Dimension(200, 50));
                btnShowByDate.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        famillionViewModel.getCostByDate(textFieldFrom.getText(), textFieldTo.getText());
                    }
                });
            }

            /** Method in charge of setting the main panel */
            private void locateComponentesAndSetMainPanel() {
                this.setPreferredSize(new Dimension(500, 250));
                this.setLayout(new BorderLayout());
                panelNorth.setBackground(colorNorthPanel);
                panelNorth.add(lblReportByDate);
                this.add(panelNorth, BorderLayout.NORTH);
                panelCenterTop.add(lblFrom);
                panelCenterTop.add(textFieldFrom);
                panelCenterBottom.add(lbllTo);
                panelCenterBottom.add(textFieldTo);
                panelCenter.add(panelCenterTop);
                panelCenter.add(panelCenterBottom);
                this.add(panelCenter, BorderLayout.CENTER);
                panelSouth.add(btnShowByDate);
                this.add(panelSouth, BorderLayout.SOUTH);
            }

            /** Method in charge of setting all the JTextField:
             * fromTextField and toTextField which represents the dates range the client desires to load*/
            private void setTextFields() {
                textFieldFrom.setPreferredSize(new Dimension(200, 50));
                textFieldFrom.setFont(new Font("Narkisim", Font.BOLD, 30));
                textFieldTo.setPreferredSize(new Dimension(200, 50));
                textFieldTo.setFont(new Font("Narkisim", Font.BOLD, 30));

            }

            /** Set JLabel lblTo before textFieldTo*/
            private void setLabelTo() {
                lbllTo.setFont(new Font("Narkisim", Font.BOLD, 30));
                lbllTo.setHorizontalAlignment(SwingConstants.CENTER);
                lbllTo.setPreferredSize(new Dimension(200, 50));
            }

            /** Set JLabel lblTo before textFieldFrom*/
            private void setLabelFrom() {
                lblFrom.setFont(new Font("Narkisim", Font.BOLD, 30));
                lblFrom.setHorizontalAlignment(SwingConstants.CENTER);
                lblFrom.setPreferredSize(new Dimension(200, 50));
            }

            /** Set JLabel lblReportByDate*/
            private void setReportByDateLabel() {
                lblReportByDate.setFont(new Font("Narkisim", Font.BOLD, 30));
                lblReportByDate.setHorizontalAlignment(SwingConstants.CENTER);
                lblReportByDate.setPreferredSize(new Dimension(400, 50));
            }

        }

        /** Report by category panel*/
        public class ReportByCategoryPanel extends JPanel {

            /** Swing components*/
            private JLabel lblReportByCategory;
            private JLabel lblCategory;
            private JComboBox<Category> comboBoxCategory;
            private JButton btnShowCategory;
            private JPanel northPanel;
            private JPanel centerPanel;
            private JPanel southPanel;
            private FlowLayout centerPanelFlowLayout;

            /** ReportByCategoryPanel constructor with all the swing components initilization*/
            public ReportByCategoryPanel() {
                northPanel = new JPanel();
                centerPanel = new JPanel(centerPanelFlowLayout);
                centerPanelFlowLayout = new FlowLayout();
                southPanel = new JPanel();
                btnShowCategory = new JButton("Show");
                comboBoxCategory = new JComboBox<Category>();
                lblCategory = new JLabel("Category:");
                lblReportByCategory = new JLabel("Report by Category");
            }

            /** Panel start method for linking and setting all the components*/
            private void start() {
                setLabelReportByCategory();
                setLabelCategory();
                setComboBoxCategory();
                setBtnShowCategory();
                locateComponentsAndSetMainPanel();
            }

            /** Locating all components and adding them to the appropriate panel*/
            private void locateComponentsAndSetMainPanel() {
                this.setLayout(new BorderLayout());
                northPanel.setBackground(colorNorthPanel);
                northPanel.add(lblReportByCategory);
                this.add(northPanel, BorderLayout.NORTH);
                centerPanelFlowLayout.setHgap(50);
                centerPanelFlowLayout.setVgap(35);
                centerPanel.setLayout(centerPanelFlowLayout);
                centerPanel.add(lblCategory);
                centerPanel.add(comboBoxCategory);
                this.add(centerPanel, BorderLayout.CENTER);
                southPanel.add(btnShowCategory);
                this.add(southPanel, BorderLayout.SOUTH);
            }
            /** Setting the button in charge of sending the desired category */
            private void setBtnShowCategory() {
                btnShowCategory.setPreferredSize(new Dimension(200, 50));
                btnShowCategory.setFont(new Font("Narkisim", Font.BOLD, 30));
                //set the button to send the selected category to the view model
                btnShowCategory.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        famillionViewModel.getCostByCategory(new Category(comboBoxCategory.getSelectedItem().toString()));
                    }
                });
            }

            /** Setting the combo box */
            private void setComboBoxCategory() {
                comboBoxCategory.setPreferredSize(new Dimension(200, 50));
                comboBoxCategory.setFont(new Font("Narkisim", Font.BOLD, 30));
            }

            /** Setting the label next to the combo box */
            private void setLabelCategory() {
                lblCategory.setPreferredSize(new Dimension(150, 50));
                lblCategory.setFont(new Font("Narkisim", Font.BOLD, 30));
            }

            /** Setting the label which acts as the title of the panel */
            private void setLabelReportByCategory() {
                lblReportByCategory.setFont(new Font("Narkisim", Font.BOLD, 30));
                lblReportByCategory.setHorizontalAlignment(SwingConstants.CENTER);
                lblReportByCategory.setPreferredSize(new Dimension(400, 50));
            }


        }

        /** Add category panel*/
        public class AddCategoryPanel extends JPanel {

            /** Swing components*/
            private JLabel lblAddCategory;
            private JLabel lblCategoryName;
            private JTextField textFieldCategoryName;
            private JButton btnShowCategory;
            private JPanel northPanel;
            private JPanel centerPanel;
            private JPanel southPanel;
            private FlowLayout centerPanelFlowLayout;

            /** AddCategoryPanel constructor in charge of initialing the components */
            public AddCategoryPanel() {
                northPanel = new JPanel();
                centerPanelFlowLayout = new FlowLayout();
                centerPanel = new JPanel();
                southPanel = new JPanel();
                btnShowCategory = new JButton("Add");
                textFieldCategoryName = new JTextField();
                lblCategoryName = new JLabel("Category Name:");
                lblAddCategory = new JLabel("Add Category");
            }

            /** Linking and setting all the components */
            private void start() {
                setLabelReportByCategory();
                setLabelCategory();
                setTextFieldCategory();
                setBtnAddCategory();
                locateComponentsAndSetMainPanel();
            }

            /** Locating, linking and setting all the class panels and layouts */
            private void locateComponentsAndSetMainPanel() {
                this.setBounds(25, 25, 450, 300);
                this.setLayout(new BorderLayout());
                northPanel.setBackground(colorNorthPanel);
                northPanel.add(lblAddCategory);
                this.add(northPanel, BorderLayout.NORTH);
                centerPanelFlowLayout.setHgap(50);
                centerPanelFlowLayout.setVgap(30);
                centerPanel.setSize(300, 100);
                centerPanel.setLayout(centerPanelFlowLayout);
                centerPanel.add(lblCategoryName);
                centerPanel.add(textFieldCategoryName);
                this.add(centerPanel, BorderLayout.CENTER);
                southPanel.add(btnShowCategory);
                this.add(southPanel, BorderLayout.SOUTH);
            }

            /** Setting the button in charge of sending the desired new category to the linked view model */
            private void setBtnAddCategory() {
                btnShowCategory.setPreferredSize(new Dimension(200, 50));
                btnShowCategory.setFont(new Font("Narkisim", Font.BOLD, 30));
                // set the button to send the new written category to the view model
                btnShowCategory.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        famillionViewModel.addCategory(new Category(textFieldCategoryName.getText()));
                    }
                });
            }

            /** Setting the text field which will hold the desired category input string */
            private void setTextFieldCategory() {
                textFieldCategoryName.setFont(new Font("Narkisim", Font.BOLD, 30));
                textFieldCategoryName.setPreferredSize(new Dimension(200, 50));
            }

            /** Set the label next to the text field to indicate the user to write in the appropriate location */
            private void setLabelCategory() {
                lblCategoryName.setPreferredSize(new Dimension(250, 50));
                lblCategoryName.setFont(new Font("Narkisim", Font.BOLD, 30));
            }

            /** Setting the label which acts as the panel title */
            private void setLabelReportByCategory() {
                lblAddCategory.setHorizontalAlignment(SwingConstants.CENTER);
                lblAddCategory.setFont(new Font("Narkisim", Font.BOLD, 30));
                lblAddCategory.setPreferredSize(new Dimension(500, 50));
            }


        }

        /** Add cost panel*/
        public class AddCostPanel extends JPanel {

            /** Swing components*/
            private JLabel lblAddCost;
            private JLabel lblSum;
            private JLabel lblCategory;
            private JLabel lblCurrency;
            private JLabel lblDescription;
            private JLabel lblDate;
            private JTextField textFieldSum;
            private JTextField textFieldDescription;
            private JTextField textFieldDate;
            private JComboBox<Category> comboBoxCategory;
            private JComboBox<Currency> comboBoxCurrency;
            private JButton btnClear;
            private JButton btnConfirm;
            private JPanel northPanel;
            private JPanel centerPanel;
            private JPanel bottomPanel;

            /** AddCostPanel constructor initializing all the class components*/
            public AddCostPanel() {
                lblAddCost = new JLabel("Add Cost");
                btnConfirm = new JButton("Confirm");
                btnClear = new JButton("Clear");
                textFieldDate = new JTextField();
                textFieldDescription = new JTextField();
                comboBoxCurrency = new JComboBox<Currency>();
                comboBoxCategory = new JComboBox<Category>();
                textFieldSum = new JTextField();
                lblDate = new JLabel("Date:");
                lblDescription = new JLabel("Description");
                lblCurrency = new JLabel("Currency:");
                lblCategory = new JLabel("Category:");
                lblSum = new JLabel("Sum:");
            }

            /** Panel start method for linking and setting all the panels and layouts */
            private void start() {
                setComponents();
                this.setPreferredSize(new Dimension(600,500));
                BorderLayout borderLayout = new BorderLayout();
                borderLayout.setVgap(20);
                this.setLayout(borderLayout);
                northPanel = new JPanel();
                northPanel.setBackground(colorNorthPanel);
                northPanel.add(lblAddCost);
                this.add(northPanel,BorderLayout.NORTH);
                GridLayout centerLayout = new GridLayout(5,2,20,60);
                centerLayout.setVgap(20);
                centerPanel = new JPanel(centerLayout);
                centerPanel.add(lblSum);
                centerPanel.add(textFieldSum);
                centerPanel.add(lblCategory);
                centerPanel.add(comboBoxCategory);
                centerPanel.add(lblCurrency);
                centerPanel.add(comboBoxCurrency);
                centerPanel.add(lblDescription);
                centerPanel.add(textFieldDescription);
                centerPanel.add(lblDate);
                centerPanel.add(textFieldDate);
                this.add(centerPanel,BorderLayout.CENTER);
                FlowLayout bottomLayout = new FlowLayout();
                bottomLayout.setHgap(50);
                bottomPanel = new JPanel(bottomLayout);
                bottomPanel.add(btnClear);
                bottomPanel.add(btnConfirm);
                this.add(bottomPanel,BorderLayout.SOUTH);
            }

            /** Method in charge of orchestrating all the setting method on by one */
            private void setComponents() {
                setLblAddCost();
                setLblSum();
                setLblCategory();
                setLblCurrency();
                setLblDescription();
                setLblDate();
                setTextFieldSum();
                setComboBoxCategory();
                setComboBoxCurrency();
                setTextFieldDescription();
                setTextFieldDate();
                setBtnClear();
                setBtnConfirm();
            }

            /** Setting the label indicating the user to type a cost */
            private void setLblAddCost() {
                lblAddCost.setHorizontalAlignment(SwingConstants.CENTER);
                lblAddCost.setFont(new Font("Narkisim", Font.BOLD, 30));
                lblAddCost.setPreferredSize(new Dimension(500,50));
            }

            /** Setting the button im charge of sending all the new cost data to the view model */
            private void setBtnConfirm() {
                //setting the button
                btnConfirm.setFont(new Font("Narkisim", Font.BOLD, 30));
                btnConfirm.setPreferredSize(new Dimension(150,50));
                // linking between the components to the view model method that gets it
                btnConfirm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            famillionViewModel.addNewCost(new Cost(
                                    Long.parseLong(textFieldSum.getText()),
                                    new Category(comboBoxCategory.getSelectedItem().toString()),
                                    (Currency) comboBoxCurrency.getSelectedItem(),
                                    textFieldDescription.getText(),
                                    textFieldDate.getText()
                            ));
                        }catch (NumberFormatException nfe){
                            FamillionView.this.showFeedbackMessage("Sum must not be empty or non round number input",1);
                        }


                    }
                });
            }

            /** Setting the button in charge to clear if the user want to erase everything */
            private void setBtnClear() {
                // setting the button
                btnClear.setFont(new Font("Narkisim", Font.BOLD, 30));
                btnClear.setPreferredSize(new Dimension(150,50));
                // clearing all the components inputs
                btnClear.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        textFieldSum.setText("");
                        textFieldDate.setText("");
                        textFieldDescription.setText("");
                        textFieldDate.setText("");
                    }
                });
            }

            /** Setting the text field in charge of the user desired cost date */
            private void setTextFieldDate() {
                textFieldDate.setFont(new Font("Narkisim", Font.BOLD, 30));
                textFieldDate.setPreferredSize(new Dimension(200,50));
            }

            /** Setting the text field in charge of the user desired cost description */
            private void setTextFieldDescription() {
                textFieldDescription.setFont(new Font("Narkisim", Font.BOLD, 30));
                textFieldDescription.setPreferredSize(new Dimension(200,50));

            }

            /** Setting the combo box in charge of the user desired cost currency */
            private void setComboBoxCurrency() {
                comboBoxCurrency.setFont(new Font("Narkisim", Font.BOLD, 30));
                comboBoxCurrency.setPreferredSize(new Dimension(200,50));

            }

            /** Setting the text field in charge of the user desired cost date */
            private void setComboBoxCategory() {
                comboBoxCategory.setFont(new Font("Narkisim", Font.BOLD, 30));
                comboBoxCategory.setPreferredSize(new Dimension(200,50));

            }

            /** Setting the text field in charge of the user desired cost sum */
            private void setTextFieldSum() {
                textFieldSum.setFont(new Font("Narkisim", Font.BOLD, 30));
                textFieldSum.setPreferredSize(new Dimension(200,50));

            }

            /** Setting the label indicating the user to write the cost date */
            private void setLblDate() {
                lblDate.setHorizontalAlignment(SwingConstants.CENTER);
                lblDate.setFont(new Font("Narkisim", Font.BOLD, 30));
                lblDate.setPreferredSize(new Dimension(200,50));
            }

            /** Setting the label indicating the user to write the cost description */
            private void setLblDescription() {
                lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
                lblDescription.setFont(new Font("Narkisim", Font.BOLD, 30));
                lblDescription.setPreferredSize(new Dimension(200,50));
            }

            /** Setting the label indicating the user to write the cost currency */
            private void setLblCurrency() {
                lblCurrency.setHorizontalAlignment(SwingConstants.CENTER);
                lblCurrency.setFont(new Font("Narkisim", Font.BOLD, 30));
                lblCurrency.setPreferredSize(new Dimension(200,50));
            }

            /** Setting the label indicating the user to select the cost Category */
            private void setLblCategory() {
                lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
                lblCategory.setFont(new Font("Narkisim", Font.BOLD, 30));
                lblCategory.setPreferredSize(new Dimension(200, 50));
            }

            /** Setting the label indicating the user to write the cost sum amount*/
            private void setLblSum() {
                lblSum.setHorizontalAlignment(SwingConstants.CENTER);
                lblSum.setFont(new Font("Narkisim", Font.BOLD, 30));
                lblSum.setPreferredSize(new Dimension(200, 50));
            }

        }

        /** Manage category panel*/
        public class ManageCategoriesPanel extends JPanel {

            /** Swing components*/
            private JLabel lblAddCategory;
            private JLabel lblCategoryName;
            private JComboBox<Category> comboBoxCategoryName;
            private JButton btnDelete;
            private JPanel northPanel;
            private JPanel centerPanel;
            private JPanel southPanel;
            private FlowLayout centerPanelFlowLayout;

            /** ManageCategoriesPanel in charge of initializing all the appropriate */
            public ManageCategoriesPanel() {
                lblAddCategory = new JLabel("Manage Categories");
                lblCategoryName = new JLabel("Select Category:");
                comboBoxCategoryName = new JComboBox<Category>();
                btnDelete = new JButton("Delete");
                northPanel = new JPanel();
                centerPanelFlowLayout = new FlowLayout();
                centerPanel = new JPanel(centerPanelFlowLayout);
                southPanel = new JPanel();
            }

            /** Panel start method for linking and setting all the components */
            private void start() {
                setLabelReportByCategory();
                setLabelCategory();
                setComboBoxCategory();
                setBtnDeleteCategory();
                locateComponentsAndSetMainPanel();
            }

            /** Locating and setting all the panel and layout of the class */
            private void locateComponentsAndSetMainPanel() {
                this.setBounds(25,25,500,300);
                this.setLayout(new BorderLayout());
                northPanel.setBackground(colorNorthPanel);
                northPanel.add(lblAddCategory);
                this.add(northPanel, BorderLayout.NORTH);
                centerPanelFlowLayout.setHgap(50);
                centerPanelFlowLayout.setVgap(30);
                centerPanel.setSize(300,100);
                centerPanel.add(lblCategoryName);
                centerPanel.add(comboBoxCategoryName);
                this.add(centerPanel,BorderLayout.CENTER);
                southPanel.add(btnDelete);
                this.add(southPanel,BorderLayout.SOUTH);
            }

            /** Setting the button in charge of sending the desired category to delete to the linked view model */
            private void setBtnDeleteCategory() {
                btnDelete.setPreferredSize(new Dimension(200,50));
                btnDelete.setFont(new Font("Narkisim", Font.BOLD, 30));
                btnDelete.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        famillionViewModel.deleteCategory(new Category(comboBoxCategoryName.getSelectedItem().toString()));
                    }
                });
            }

            /** Setting the combo box which show the available categories */
            private void setComboBoxCategory() {
                comboBoxCategoryName.setFont(new Font("Narkisim", Font.BOLD, 30));
                comboBoxCategoryName.setPreferredSize(new Dimension(200,50));
            }

            /** Setting the label indicating the user there to select desired category to the delete */
            private void setLabelCategory() {
                lblCategoryName.setPreferredSize(new Dimension(250,50));
                lblCategoryName.setFont(new Font("Narkisim", Font.BOLD, 30));
            }

            /** Setting the label which acts as the title of the panel */
            private void setLabelReportByCategory() {
                lblAddCategory.setHorizontalAlignment(SwingConstants.CENTER);
                lblAddCategory.setFont(new Font("Narkisim", Font.BOLD, 30));
                lblAddCategory.setPreferredSize(new Dimension(500,50));
            }

        }

    }

}
