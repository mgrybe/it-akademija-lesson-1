package lt.itakademija.hello;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.util.concurrent.Semaphore;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingView implements View, Initializable, Destroyable {
	
	private JFrame frame;
	
	private NamesDisplayingPanel nameDisplay;
	
	private Semaphore semaphore = new Semaphore(1);

	@Override
	public void showName(String name) {
		this.nameDisplay.setDisplayName(name);
		this.nameDisplay.repaint();
		
		acquire();
	}
	
	private void acquire() {
		try {
			this.semaphore.acquire(1);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void init() {
		this.frame = new JFrame();
		this.frame.setLayout(new BorderLayout());
		this.nameDisplay = new NamesDisplayingPanel();
		this.frame.add(this.nameDisplay, BorderLayout.CENTER);
		
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(500, 500);
		this.frame.setVisible(true);
		
		acquire();
		
		this.frame.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				SwingView.this.semaphore.release();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	private static final class NamesDisplayingPanel extends JPanel {
		private String displayName;
		
		void setDisplayName(String name) {
			this.displayName = name;
		}
		
		@Override
		public Color getBackground() {
			return Color.WHITE;
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			setBackground(Color.white);
			int w = getSize().width;
			int h = getSize().height;
			
			Graphics2D g2 = (Graphics2D) g;

			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

			FontRenderContext frc = g2.getFontRenderContext();
			Font f = new Font("Helvetica", 1, w / 15);
			String s = new String(this.displayName);
			TextLayout textTl = new TextLayout(s, f, frc);
			AffineTransform transform = new AffineTransform();
			Shape outline = textTl.getOutline(null);
			
			Rectangle r = outline.getBounds();
			transform = g2.getTransform();
			transform.translate(w / 2 - (r.width / 2), h / 2 + (r.height / 2));
			//transform.scale(0.5, 0.5);
			
			g2.transform(transform);
			g2.setColor(Color.black);
			g2.draw(outline);
			g2.fill(outline);
			g2.setClip(outline);
		}
	}

	@Override
	public void destroy() {
		frame.dispose();
	}

}
