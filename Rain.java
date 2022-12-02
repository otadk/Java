import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.MemoryImageSource;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Rain extends JDialog implements ActionListener {

	private Random random                     = new Random();
	private JPanel graphicsPanel              = new GraphicsPanel();
	private Dimension screenSize              = Toolkit.getDefaultToolkit().getScreenSize();
	private final static int gap              = 15;
	private int lines                         = screenSize.height / gap;
	private int columns                       = screenSize.width / gap;
	private int[] posArr                      = new int[columns + 1];
	private Color backgroundColor             = Color.BLACK;
	private KeyPressListener keyPressListener = new KeyPressListener();

	public Rain() {
		this.setLayout(new BorderLayout());
		this.add(graphicsPanel, BorderLayout.CENTER);
		this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(0, 0, null, 0, 0)), new Point(0, 0), "cursor"));
		this.addKeyListener(this.keyPressListener);
		this.setUndecorated(true);
		this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		for (int i = 0; i < posArr.length; i++) {
			//posArr[i] = random.nextInt(lines);
			posArr[i] = 0;
		}
		new Timer(250, this).start(); // 帧数调控，1000/delay帧每秒
	}

	private char getChr() {
		return (char) (random.nextInt(26) + (int)('a'));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		graphicsPanel.repaint();
	}

	private class GraphicsPanel extends JPanel {

		@Override
		public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setFont(getFont().deriveFont(Font.BOLD));
			g2d.setColor(backgroundColor);
			g2d.fillRect(0, 0, screenSize.width, screenSize.height);
			int currentColumn = 0;
			for (int x = 0; x < screenSize.width; x += gap, currentColumn++) {
				int endPos = posArr[currentColumn];
				g2d.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
				g2d.drawString(String.valueOf(getChr()), x, endPos * gap);
				for (int j = lines - 30; j < endPos; j++) {
					g2d.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
					g2d.drawString(String.valueOf(getChr()), x, j * gap);
				}
				posArr[currentColumn] += 1;
				if (posArr[currentColumn] * gap > getHeight()) {
					posArr[currentColumn] = 0;
				}
			}
		}
	}

	private class KeyPressListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			backgroundColor = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				
			}
		}
	}

	public static void main(String[] args) {
		new Rain();
	}
}
