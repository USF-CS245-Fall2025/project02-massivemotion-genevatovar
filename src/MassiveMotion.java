import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

/**
 * MassiveMotion creates celestial objects moving on a canvas such as a central star and random comets.
 * It does this by creating and maintaining a List of celestial objects.
 */
public class MassiveMotion extends JPanel implements ActionListener {
    /**
     * CelestialObject class for storing the celestial objects attributes such as size, mass, velocity, and position
     */
    public static class CelestialObject {
        private int position_x, position_y;
        private final int size;
        private final double mass;
        private final int velocity_x;
        private final int velocity_y;
        private final boolean isStar;

        /**
         * Constructor for CelestialObject to initialize new objects of the class
         * @param pos_x initial x position
         * @param pos_y initial y position
         * @param s size/radius of the object
         * @param m mass of the object in kg
         * @param vel_x velocity along the x-axis
         * @param vel_y velocity along the y-axis
         * @param star boolean to determine if the object is the central star
         */
        public CelestialObject (int pos_x, int pos_y, int s, double m, int vel_x, int vel_y, boolean star) {
            position_x = pos_x;
            position_y = pos_y;
            size = s;
            mass = m;
            velocity_x = vel_x;
            velocity_y = vel_y;
            isStar = star;
        }

        /**
         * @return x position of the celestial object
         */
        public int get_position_x() { return position_x; }

        /**
         * @return y position of the celestial object
         */
        public int get_position_y() { return position_y; }

        /**
         * @return size/radius of the celestial object
         */
        public int get_size() { return size; }

        /**
         * @return mass of the celestial object
         */
        public double get_mass() { return mass; }

        /**
         * @return x velocity of the celestial object
         */
        public int get_velocity_x() { return velocity_x; }

        /**
         * @return y velocity of the celestial object
         */
        public int get_velocity_y() { return velocity_y; }

        /**
         * @return true if celestial object is a star, false otherwise
         */
        public boolean isStar() { return isStar; }

        /**
         *  Updates the position of the celestial object based on velocity (position += velocity per frame)
         */
        public void updatePosition() {
            position_x += velocity_x;
            position_y += velocity_y;
        }

        /**
         * Checks if the object is outside the canvas bounds
         * @param window_x width (x) of the window
         * @param window_y height (y) of the window
         * @return true if object is off-screen
         */
        public boolean isOffScreen(int window_x, int window_y) {
            if (position_x < - size || position_x > window_x + size ||
                    position_y < - size || position_y > window_y + size) {
                return true; // object off-screen
            }
            return false;
        }
    }

    /** Timer for animation updates */
    protected Timer tm;
    /** Delay between timer updates in ms */
    protected int timer_delay;
    /** Type of list implementation used */
    protected String list;
    /** Width and height of the canvas window in pixels */
    protected int window_size_x, window_size_y;
    /** Probability of generating comets on x-axis, y-axis, and mass of comets in kg*/
    protected double gen_x, gen_y, body_mass;
    /** Size/radius of generated comet objects in pixels and max velocity range for generated comets*/
    protected int body_size, body_velocity;
    /** Initial x and y position of the central star, size/radius of the star, and velocity of the star along the x and y axis*/
    protected int star_position_x, star_position_y, star_size, star_velocity_x, star_velocity_y;
    /** Mass of star in kg */
    protected double star_mass;
    /** List holding all celestial objects */
    protected List<CelestialObject> celestialObjectsList;
    /** Random number generator for generating comets */
    protected static Random rand;

    /**
     * Default constructor that uses the default configuration file
     */
    public MassiveMotion () {
        this("MassiveMotion.txt");
    }

    /**
     * Constructor that reads configuration from a property file
     * @param propFile name of the property file to be read
     */
     public MassiveMotion(String propFile) {
        rand = new Random();
        System.out.println("MassiveMotion Constructor begins");
        System.out.println("Reading config file: " + propFile);

        Properties prop = new Properties();
         try {
             // Try to load from file system
             InputStream is;
             try {
                 is = new FileInputStream(propFile);
                 System.out.println("Config file " + propFile + " found and opened.");
             } catch (IOException e) {
                 // Try to load from resources if file cannot be found
                 is = getClass().getResourceAsStream(propFile);
                 if (is == null) { throw new IOException("Cannot find config file: " + propFile); }
                 System.out.println("Config file loaded from resources.");
             }
             prop.load(is);

             timer_delay = Integer.parseInt(prop.getProperty("timer_delay"));
             list = prop.getProperty("list");

             System.out.println("\nConfiguration loaded.");
             System.out.println("Timer delay: " + timer_delay + "ms");
             System.out.println("List type: " + list);

             window_size_x = Integer.parseInt(prop.getProperty("window_size_x"));
             window_size_y = Integer.parseInt(prop.getProperty("window_size_y"));
             System.out.println("Window size: " + window_size_x + " x " + window_size_y);

             gen_x = Double.parseDouble(prop.getProperty("gen_x"));
             gen_y = Double.parseDouble(prop.getProperty("gen_y"));
             System.out.println("Generation probability - X: " + gen_x + ", Y: " + gen_y);

             body_size = Integer.parseInt(prop.getProperty("body_size"));
             body_mass = Double.parseDouble(prop.getProperty("body_mass"));
             body_velocity = Integer.parseInt(prop.getProperty("body_velocity"));
             System.out.println("Comet properties - Size: " + body_size + ", Velocity range: ±" + body_velocity);

             star_position_x = Integer.parseInt(prop.getProperty("star_position_x"));
             star_position_y = Integer.parseInt(prop.getProperty("star_position_y"));
             star_size = Integer.parseInt(prop.getProperty("star_size"));
             star_mass = Double.parseDouble(prop.getProperty("star_mass"));
             star_velocity_x = Integer.parseInt(prop.getProperty("star_velocity_x"));
             star_velocity_y = Integer.parseInt(prop.getProperty("star_velocity_y"));
             System.out.println("Star properties - Position: (" + star_position_x + "," + star_position_y +
                     "), Size: " + star_size + ", Velocity: (" + star_velocity_x + "," + star_velocity_y + ")");

             celestialObjectsList = createList(list);

             // Create star object first using the configurations from the property file
             CelestialObject star = new CelestialObject(star_position_x, star_position_y,
                     star_size, star_mass, star_velocity_x, star_velocity_y, true);
             celestialObjectsList.add(star);
             System.out.println("Star created and added to list");
             System.out.println("List size after star: " + celestialObjectsList.size());

             generateInitialComets();

             is.close();
             System.out.println("Constructor Complete\n");
         } catch (IOException e) {
             System.err.println("Error reading property file: " + e.getMessage());
         }
         tm = new Timer(timer_delay, this); // First argument with delay with value from config file.
         System.out.println("Timer created with delay: " + timer_delay + " ms\n");
     }

    /**
     * Creates List implementation based on config list value
     * @param list type of list from the property file
     * @return List implementation being used
     */
     private List<CelestialObject> createList(String list) {
         return switch (list.toLowerCase()) {
             case "single" -> new LinkedList<>();
             case "double" -> new DoublyLinkedList<>();
             case "dummyhead" -> new DummyHeadLinkedList<>();
             default -> new ArrayList<>();
         };
     }

    /**
     * Paints the canvas with all celestial objects
     * @param g Graphics object
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            // Draws each object, star is red and other objects are black
            for (int i = 0; i < celestialObjectsList.size(); i++) {
                CelestialObject obj = celestialObjectsList.get(i);
                if (obj.isStar()) {
                    g.setColor(Color.RED);
                } else {
                    g.setColor(Color.BLACK);
                }
                // Draws an oval at the object's position
                g.fillOval(obj.get_position_x() - obj.get_size() / 2,
                        obj.get_position_y() - obj.get_size() / 2,
                        obj.get_size(), obj.get_size());
            }
        } catch (Exception e) {
            System.err.println("Error painting celestial objects: " + e.getMessage());
        }
        tm.start();
    }

    /**
     * Uses timer events to update celestial object positions
     * @param actionEvent the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Generate new comets based on probability
        if (rand.nextDouble() < gen_x) {
            generateComet(true); // generate on x axis
        }
        if (rand.nextDouble() < gen_y) {
            generateComet(false); // generate on y axis
        }
        // Update objects position
        try {
            // Iterates backwards to avoid skipping elements
            for (int i = celestialObjectsList.size() - 1; i >= 0; i--) {
                CelestialObject obj = celestialObjectsList.get(i);
                obj.updatePosition();
                // Remove objects off-screen
                if (obj.isOffScreen(window_size_x, window_size_y) && !obj.isStar()) {
                    celestialObjectsList.remove(i);
                }
            }
        } catch (Exception e) {
            System.err.println("Error updating celestial objects: " + e.getMessage());
        }
        repaint();
    }

    /**
     * Generates a new comet at the edge of the canvas
     * @param on_x_axis true to generate on x-axis (top/bottom), false for y-axis (left/right)
     */
    private void generateComet(boolean on_x_axis) {
        int x, y, vel_x, vel_y;

        if (on_x_axis) {
            // Generate on top or bottom edge
            x = rand.nextInt(window_size_x);
            if (rand.nextBoolean()) {
                y = 0;
            } else {
                y = window_size_y;
            }
        } else {
            // Generate on left or right edge
            if (rand.nextBoolean()) {
                x = 0;
            } else {
                x = window_size_x;
            }
            y = rand.nextInt(window_size_y);
        }
        vel_x = getRandomVelocity();
        vel_y = getRandomVelocity();

        createEdgeComet(x, y, vel_x, vel_y);
    }

    /**
     * Generates the initial comets at the edges of the canvas
     */
    private void generateInitialComets() {
        int numInitialComets = 5;
        for (int i = 0; i < numInitialComets; i++) {
            // Top edge (moves downward)
            createEdgeComet(rand.nextInt(window_size_x), 0,
                    getRandomVelocity(), rand.nextInt(body_velocity) + 1);
            // Bottom edge (moves upward)
            createEdgeComet(rand.nextInt(window_size_x), window_size_y,
                    getRandomVelocity(), -(rand.nextInt(body_velocity) + 1));
            // Left edge (moves right)
            createEdgeComet(0, rand.nextInt(window_size_y),
                    rand.nextInt(body_velocity) + 1, getRandomVelocity());
            // Right edge (moves left)
            createEdgeComet(window_size_x, rand.nextInt(window_size_y),
                    -(rand.nextInt(body_velocity) + 1), getRandomVelocity());
        }
    }

    /**
     * Creates a comet at a position with a specified velocity
     * @param pos_x x position
     * @param pos_y y position
     * @param vel_x x velocity
     * @param vel_y y velocity
     */
    private void createEdgeComet(int pos_x, int pos_y, int vel_x, int vel_y) {
        CelestialObject comet = new CelestialObject(pos_x, pos_y, body_size, body_mass, vel_x, vel_y, false);
        celestialObjectsList.add(comet);
    }

    /**
     * Gets a random velocity in range [-body_velocity, +body_velocity] not zero
     * @return random velocity value
     */
    private int getRandomVelocity() {
        int velocity = rand.nextInt(body_velocity * 2 + 1) - body_velocity;
        if (velocity == 0) { return 1; }
        else { return velocity; }
    }

    /**
     * Main method to start the application
     * @param args command line arguments (property file name)
     */
    public static void main(String[] args) {
        System.out.println("Massive Motion starting...");

        MassiveMotion mm;
        if (args.length == 0) {
            System.out.println("No config file argument provided, using default: MassiveMotion.txt");
            mm = new MassiveMotion();
        } else {
            System.out.println("Using config file: " + args[0]);
            mm = new MassiveMotion(args[0]);
        }

        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setSize(mm.window_size_x, mm.window_size_y);
        jf.add(mm);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

// Code for reading from configuration file taken from: https://kodejava.org/how-do-i-read-a-configuration-file-using-java-util-properties/
// Used to refresh my memory on switch case statement and used arrows to improve readability: https://nipafx.dev/java-switch/#:~:text=Some%20problems%20can%20only%20be,for%20a%20few%20minor%20reasons.
