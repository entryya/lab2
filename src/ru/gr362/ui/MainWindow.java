package ru.gr362.ui;

import ru.gr362.math.InterpolatingPolynomial;
import ru.gr362.converting.Converter;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.function.Consumer;

public class MainWindow extends JFrame {
    private static final int MIN_SZ = GroupLayout.PREFERRED_SIZE;
    private static final int MAX_SZ = GroupLayout.DEFAULT_SIZE;

    private final Converter converter = new Converter(-5.0, 5.0, -5.0, 5.0, 800, 600);

    private final CartesianPainter cp = new CartesianPainter(converter);
    private final PointsPainter pointsPainter = new PointsPainter(converter);

    private final FunctionPainter polynomialPainter = new FunctionPainter(converter);
    private final FunctionPainter derivativePainter = new FunctionPainter(converter);

    private InterpolatingPolynomial interpolatingPolynomial;

    private JPanel mainPanel;
    private JPanel controlPanel;

    private JLabel lblXMin;
    private JLabel lblXMax;
    private JLabel lblYMin;
    private JLabel lblYMax;

    private JSpinner spXMin;
    private JSpinner spXMax;
    private JSpinner spYMin;
    private JSpinner spYMax;

    private SpinnerNumberModel nmXMin;
    private SpinnerNumberModel nmXMax;
    private SpinnerNumberModel nmYMin;
    private SpinnerNumberModel nmYMax;

    private JButton btnExit;

    private JCheckBox cbPoints;
    private JCheckBox cbPolynomial;
    private JCheckBox cbDerivative;
    private JPanel pPoints;
    private JPanel pPolynomial;
    private JPanel pDerivative;

    public MainWindow(){
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initComponents();

        var gl = new GroupLayout(getContentPane());
        setLayout(gl);

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addComponent(mainPanel, MAX_SZ, MAX_SZ, MAX_SZ)
                .addGap(8)
                .addComponent(controlPanel, MIN_SZ, MIN_SZ, MIN_SZ)
                .addGap(8)
        );

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addGroup(gl.createParallelGroup()
                        .addComponent(mainPanel, MAX_SZ, MAX_SZ, MAX_SZ)
                        .addComponent(controlPanel, MAX_SZ, MAX_SZ, MAX_SZ)
                )
                .addGap(8)
        );

        GroupLayout glSettings = new GroupLayout(controlPanel);
        controlPanel.setLayout(glSettings);

        glSettings.setVerticalGroup(glSettings.createSequentialGroup()
                .addGap(8)
                .addGroup(glSettings.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(glSettings.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addGroup(glSettings.createSequentialGroup()
                                        .addComponent(lblXMin, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addGap(8)
                                        .addComponent(lblYMin, MIN_SZ, MIN_SZ, MIN_SZ)
                                )
                                .addGroup(glSettings.createSequentialGroup()
                                        .addComponent(spXMin, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addGap(8)
                                        .addComponent(spYMin, MIN_SZ, MIN_SZ, MIN_SZ)
                                )
                                .addGroup(glSettings.createSequentialGroup()
                                        .addComponent(lblXMax, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addGap(8)
                                        .addComponent(lblYMax, MIN_SZ, MIN_SZ, MIN_SZ)
                                )
                                .addGroup(glSettings.createSequentialGroup()
                                        .addComponent(spXMax, MIN_SZ, MIN_SZ, MIN_SZ)
                                        .addGap(8)
                                        .addComponent(spYMax, MIN_SZ, MIN_SZ, MIN_SZ)
                                )
                                .addGroup(glSettings.createSequentialGroup()
                                        .addComponent(cbPoints, MIN_SZ,MIN_SZ, MIN_SZ)
                                        .addGap(8)
                                        .addComponent(cbPolynomial, MIN_SZ,MIN_SZ, MIN_SZ)
                                        .addGap(8)
                                        .addComponent(cbDerivative, MIN_SZ,MIN_SZ, MIN_SZ)
                                )
                                .addGroup(glSettings.createSequentialGroup()
                                        .addComponent(pPoints, 24, 24, 24)
                                        .addGap(8)
                                        .addComponent(pPolynomial, 24, 24, 24)
                                        .addGap(8)
                                        .addComponent(pDerivative, 24, 24, 24)

                                )
                        )
                        .addComponent(btnExit, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(8)
        );

        glSettings.setHorizontalGroup(glSettings.createSequentialGroup()
                .addGap(8)
                .addGroup(glSettings.createParallelGroup()
                        .addComponent(lblXMin, MIN_SZ, MIN_SZ, MIN_SZ)
                        .addComponent(lblYMin, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(8)
                .addGroup(glSettings.createParallelGroup()
                        .addComponent(spXMin, 100, MIN_SZ, MIN_SZ)
                        .addComponent(spYMin, 100, MIN_SZ, MIN_SZ)
                )
                .addGap(8, 8, 100)
                .addGroup(glSettings.createParallelGroup()
                        .addComponent(lblXMax, MIN_SZ, MIN_SZ, MIN_SZ)
                        .addComponent(lblYMax, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(8)
                .addGroup(glSettings.createParallelGroup()
                        .addComponent(spXMax, 100, MIN_SZ, MIN_SZ)
                        .addComponent(spYMax, 100, MIN_SZ, MIN_SZ)
                )
                .addGap(8, 8, Integer.MAX_VALUE)
                .addGroup(glSettings.createParallelGroup()
                        .addComponent(cbPoints, MIN_SZ, MIN_SZ, MIN_SZ)
                        .addComponent(cbPolynomial, MIN_SZ, MIN_SZ, MIN_SZ)
                        .addComponent(cbDerivative, MIN_SZ, MIN_SZ, MIN_SZ)
                )
                .addGap(8)
                .addGroup(glSettings.createParallelGroup()
                        .addComponent(pPoints, 24, 24, 24)
                        .addComponent(pPolynomial, 24, 24, 24)
                        .addComponent(pDerivative, 24, 24, 24)
                )
                .addGap(8)
                .addComponent(btnExit, MIN_SZ, MIN_SZ, MIN_SZ)
                .addGap(8)
        );
    }


    private void initComponents(){
        mainPanel = new JPanel(){
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                cp.paint(g);

                if (cbPoints.isSelected()) {
                    pointsPainter.paint(g);
                }
                if (cbPolynomial.isSelected()) {
                    polynomialPainter.paint(g);
                }
                if (cbDerivative.isSelected()) {
                    derivativePainter.paint(g);
                }
            }
        };
        mainPanel.setBackground(Color.WHITE);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                int w = mainPanel.getWidth();
                int h = mainPanel.getHeight();

                converter.setWidth(w);
                converter.setHeight(h);

                cp.setWidth(w);
                cp.setHeight(h);

                polynomialPainter.setWidth(w);
                polynomialPainter.setHeight(h);

                derivativePainter.setWidth(w);
                derivativePainter.setHeight(h);

                mainPanel.repaint();
            }
        });

        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var clickPoint = e.getPoint();

                double x = converter.xScr2Crt(clickPoint.x);
                double y = converter.yScr2Crt(clickPoint.y);

                if (SwingUtilities.isLeftMouseButton(e)) {
                    pointsPainter.addPoint(x, y);
                    if (interpolatingPolynomial == null) {
                        interpolatingPolynomial = new InterpolatingPolynomial(Map.of(x, y));
                    } else {
                        interpolatingPolynomial.addPoint(x, y);
                    }
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    if (!pointsPainter.getPoints().isEmpty()) {
                        pointsPainter.deletePoint(x, y);
                        interpolatingPolynomial.deletePoint(x, y);
                        // чтобы, если до удаления в целом только одна точка и была, не рисовался полином y = 0
                        if (pointsPainter.getPoints().isEmpty()) {
                            interpolatingPolynomial = null;
                        }
                    }
                }
                polynomialPainter.setFunction(interpolatingPolynomial);
                if (interpolatingPolynomial != null) {
                    derivativePainter.setFunction(interpolatingPolynomial.differentiate());
                }
                mainPanel.repaint();
            }
        });

        controlPanel = new JPanel();
        controlPanel.setBorder(
                new TitledBorder(
                        new EtchedBorder(),
                        "Настройки"
                )
        );

        lblXMin = new JLabel();
        lblXMax = new JLabel();
        lblYMin = new JLabel();
        lblYMax = new JLabel();

        lblXMin.setText("XMin");
        lblXMax.setText("XMax");
        lblYMin.setText("YMin");
        lblYMax.setText("YMax");

        nmXMin  = new SpinnerNumberModel(-5.0, -100.0,   4.9, 0.1);
        nmXMax  = new SpinnerNumberModel( 5.0, -  4.9, 100.0, 0.1);
        nmYMin  = new SpinnerNumberModel(-5.0, -100.0,   4.9, 0.1);
        nmYMax  = new SpinnerNumberModel( 5.0, -  4.9, 100.0, 0.1);

        nmXMin.addChangeListener(e -> {
            nmXMax.setMinimum((double)nmXMin.getValue() + 0.1);
            converter.setxMin((Double) nmXMin.getValue());
            mainPanel.repaint();
        });
        nmXMax.addChangeListener(e -> {
            nmXMin.setMaximum((double)nmXMax.getValue() - 0.1);
            converter.setxMax((Double) nmXMax.getValue());
            mainPanel.repaint();
        });
        nmYMin.addChangeListener(e -> {
            nmYMax.setMinimum((double)nmYMin.getValue() + 0.1);
            converter.setyMin((Double) nmYMin.getValue());
            mainPanel.repaint();
        });
        nmYMax.addChangeListener(e -> {
            nmYMin.setMaximum((double)nmYMax.getValue() - 0.1);
            converter.setyMax((Double) nmYMax.getValue());
            mainPanel.repaint();
        });

        spXMin  = new JSpinner(nmXMin);
        spXMax  = new JSpinner(nmXMax);
        spYMin  = new JSpinner(nmYMin);
        spYMax  = new JSpinner(nmYMax);

        btnExit = new JButton();
        btnExit.setText("Выход");
        btnExit.addActionListener(e -> {
            dispose();
        });

        cbPoints = new JCheckBox("Показывать точки", true);
        cbPolynomial = new JCheckBox("Показывать полином", true);
        cbDerivative = new JCheckBox("Показывать производную", false);

        cbPoints.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPanel.repaint();
            }
        });

        cbPolynomial.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPanel.repaint();
            }
        });

        cbDerivative.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPanel.repaint();
            }
        });

        pPoints = new JPanel();
        pPoints.setBackground(Color.decode("#FF7F50"));

        pPolynomial = new JPanel();
        pPolynomial.setBackground(Color.decode("#1E90FF"));

        pDerivative = new JPanel();
        pDerivative.setBackground(Color.decode("#90EE90"));

        pointsPainter.setColor(pPoints.getBackground());
        polynomialPainter.setColor(pPolynomial.getBackground());
        derivativePainter.setColor(pDerivative.getBackground());

        setupColorChooser(pPoints, "Цвет точек", pointsPainter::setColor);
        setupColorChooser(pPolynomial, "Цвет графика полинома", polynomialPainter::setColor);
        setupColorChooser(pDerivative, "Цвет графика производной", derivativePainter::setColor);
    }

    private void setupColorChooser(JPanel panel, String title, Consumer<Color> colorConsumer) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Color newColor = JColorChooser.showDialog(MainWindow.this, title, panel.getBackground());
                if (newColor != null) {
                    panel.setBackground(newColor);
                    colorConsumer.accept(newColor);
                    mainPanel.repaint();
                }
            }
        });
    }
}