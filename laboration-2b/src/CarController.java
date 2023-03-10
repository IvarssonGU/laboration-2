import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/

public class CarController extends JFrame {
    private static final int X = CarSim.frameWidth;
    private static final int Y = CarSim.frameHeight;

    // The controller member
    CarModel cm;

    DrawPanel drawPanel;// = new DrawPanel(X, Y-240);

    SpeedPanel speedPanel;

    JPanel controlPanel = new JPanel();

    JPanel gasPanel = new JPanel();
    JSpinner gasSpinner = new JSpinner();
    int gasAmount = 0;
    JLabel gasLabel = new JLabel("Amount of gas");

    JButton gasButton = new JButton("Gas");
    JButton brakeButton = new JButton("Brake");
    JButton turboOnButton = new JButton("Saab Turbo on");
    JButton turboOffButton = new JButton("Saab Turbo off");
    JButton liftBedButton = new JButton("Lift Bed");
    JButton lowerBedButton = new JButton("Lower Bed");

    JButton startButton = new JButton("Start all cars");
    JButton stopButton = new JButton("Stop all cars");
    JButton addCar = new JButton("Add Car");
    JButton removeCar = new JButton("Remove a Car");

    // Constructor
    public CarController(String framename, CarModel cm, DrawPanel dp, SpeedPanel sp){
        this.drawPanel = dp;
        this.cm = cm;
        this.speedPanel = sp;
        initComponents(framename);
    }


    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand how these methods and components work
    private void initComponents(String title) {

        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(drawPanel);
        this.add(speedPanel);

        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        gasSpinner = new JSpinner(spinnerModel);
        gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner)e.getSource()).getValue();
            }
        });

        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(gasSpinner, BorderLayout.PAGE_END);

        
        this.add(gasPanel);

        controlPanel.setLayout(new GridLayout(2,5));


        controlPanel.add(gasButton, 0);
        controlPanel.add(turboOnButton, 1);
        controlPanel.add(liftBedButton, 2);
        controlPanel.add(startButton, 3);
        controlPanel.add(addCar,4);
        controlPanel.add(brakeButton, 5);
        controlPanel.add(turboOffButton, 6);
        controlPanel.add(lowerBedButton, 7);
        controlPanel.add(stopButton, 8);
        controlPanel.add(removeCar, 9);
        controlPanel.setPreferredSize(new Dimension((X-250), 240));
        this.add(controlPanel);
        controlPanel.setBackground(Color.CYAN);

        startButton.setBackground(Color.blue);
        startButton.setForeground(Color.green);
        stopButton.setBackground(Color.red);
        stopButton.setForeground(Color.black);

        addCar.setForeground(Color.green);
        removeCar.setForeground(Color.green);

        gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.gas(gasAmount); // carModel.gas()
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.startEngine();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.stopEngine();
            }
        });

        brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.brake(gasAmount);
            }
        });

        turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.setTurboOn();
            }
        });

        turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.setTurboOff();
            }
        });

        lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.setTrailerDown();
            }
        });

        liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.setTrailerUp();
            }
        });

        addCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.addCar();
            }
        });

        removeCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cm.removeCar();
            }
        });

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}