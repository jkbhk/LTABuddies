package eu.jacquet80.minigeo;

import Buddy.Vector2;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MapPanel extends JPanel {
	private final List<Segment> segments = new ArrayList<Segment>();
	private final List<POI> pois = new ArrayList<POI>();
	private double minEasting, maxEasting, minNorthing, maxNorthing;
	private double oEasting;
        private double oNorthing;		// coordinates of the origin
	private double scale;
        public int pointSize = 10;
        
        
	public MapPanel() {
		setMinimumSize(new Dimension(400, 300));
		setPreferredSize(new Dimension(800, 600));
		
		resetMinMaxEastingNorthing();
		
		addMouseWheelListener(new MouseWheelZoomer());
		
		MousePanner mousePanner = new MousePanner();
		addMouseListener(mousePanner);
		addMouseMotionListener(mousePanner);
	}
	
	@Override
	protected synchronized void paintComponent(Graphics g_) {
		super.paintComponent(g_);
		
		Graphics2D g = (Graphics2D) g_;
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		
		int w = getWidth();
		int h = getHeight();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w, h);
		                		
                w = getWidth() / 2;
		h = getHeight() / 2;
                
		if(this.scale == -1) scale();
		if(segments.size() != 0)
                    for(Segment seg : segments) 
                    {
                        Point pA = seg.getPointA();
                        Point pB = seg.getPointB();

                        g.setColor(seg.getColor());
                        g.setStroke(seg.getStroke());
                        
                        int x1 = convertX(pA.getEasting(), w);
			int y1 = convertY(pA.getNorthing(), h) + 5;
                        
                        int x2 = convertX(pB.getEasting(), w);
			int y2 = convertY(pB.getNorthing(), h) + 5;
                                                
                        g.drawLine(x1, y1, x2, y2);
                        
                        if (seg.pEnable)
                        {
                            g.setColor(Color.ORANGE);
                            g.fill3DRect((((x1 + x2)/2) + x2) /2, (((y1 + y2)/2) + y2) /2, pointSize, pointSize, true);
                        }

                    }

		
		for(POI poi : pois) {
			int x = convertX(poi.getEasting(), w);
			int y = convertY(poi.getNorthing(), h);
                        g.setColor(poi.pointColor);
			g.fillOval(x-1, y-1, poi.pointSize, poi.pointSize);
                        
                        
                        Color tempColor = Color.BLACK;
                        
                        if(poi.nodeType == Point.NodeType.NODE)
                        {
                            x += 20;
                            y -= 20;
                            tempColor = Color.RED;
                        }
                        
                        g.setColor(tempColor);
			g.drawString(poi.getLabel(), x, y);
		}
		g.setColor(Color.BLACK);
		// unit is the unit of the scale. It must be a power of ten, such that unit * scale in [25, 250]
		double unit = Math.pow(10, Math.ceil(Math.log10(25/scale)));
		String strUnit;
		if(unit >= 1) strUnit = ((int) unit) + " km";
		else strUnit = ((int) (1000*unit)) + " m";
		g.drawString(strUnit + " \u2194 " + ((int)(unit * scale)) + " px", 10, 10+g.getFontMetrics().getHeight());
		// draw a 1-kilometer segment
		for(int i=6; i<=9; i++) {
			g.drawLine(10, i, 10+(int)(unit*scale*(i<8 ? 1 : .5)), i);
		}
	}
	
	public synchronized void clear() {
		this.segments.clear();
		this.pois.clear();
		
		resetMinMaxEastingNorthing();
	}
	
	public synchronized void addSegment(Segment segment) {
		this.segments.add(segment);
		
		updateMinMaxEastingNorthing(segment.getPointA());
		updateMinMaxEastingNorthing(segment.getPointB());
	}
	
	public void addSegments(Collection<Segment> segments) {
		for(Segment seg : segments) addSegment(seg);
	}
        
	
	public synchronized void addPOI(POI poi) {
		this.pois.add(poi);
		
		updateMinMaxEastingNorthing(poi);
	}
	
	private synchronized void updateMinMaxEastingNorthing(Point point) {
		double easting = point.getEasting();
		if(easting > maxEasting) maxEasting = easting;
		if(easting < minEasting) minEasting = easting;
		
		double northing = point.getNorthing();
		if(northing > maxNorthing) maxNorthing = northing;
		if(northing < minNorthing) minNorthing = northing;
	}
	
	private synchronized void resetMinMaxEastingNorthing() {
		minEasting = Double.MAX_VALUE;
		maxEasting = Double.MIN_VALUE;
		minNorthing =  Double.MAX_VALUE;
		maxNorthing = Double.MIN_VALUE;
		this.scale = -1;
	}
        
        public void setViewAtPoint(Vector2 position)
        {
            POI tempPOI = new POI(position.y, position.x, "");
            
            scale = 650;
            
            oEasting = tempPOI.getEasting();
            oNorthing = tempPOI.getNorthing();
            //oEasting = convertX(position.x)+ 22160.0;
//            oNorthing = convertY(position.y, getHeight())+ 5.0;
            repaint();
        }
        
        public void setViewAtPoints(Vector2 pos1, Vector2 pos2)
        {
            POI tempPOI1 = new POI(pos1.y, pos1.x, "");
            POI tempPOI2 = new POI(pos2.y, pos2.x, "");
            
            oEasting = (tempPOI1.getEasting() + tempPOI2.getEasting()) / 2;
            oNorthing = (tempPOI1.getNorthing() + tempPOI2.getNorthing()) / 2;
            
            scale = (50/ tempPOI1.distanceTo(tempPOI2));
            
            //oEasting = convertX(position.x)+ 22160.0;
//            oNorthing = convertY(position.y, getHeight())+ 5.0;
            repaint();
        }
        
        public void resetScale()
        {
            scale = 500;
            repaint();
        }
	
	private synchronized void scale() {
		int w = getWidth()/2 * 700;
		int h = getHeight()/2 * 700;
		
		this.scale = Math.min(
				w / (maxEasting - minEasting),
				h / (maxNorthing - minNorthing));
		
                
		oEasting = minEasting ;//+ 22160.0;
		oNorthing = minNorthing;// + 20.0;
	}
	
	private int applyScale(double km) {
		return (int)(km*scale);
	}
	
	private int convertX(double easting) {
		return applyScale(easting - oEasting);
	}	
        private int convertX(double easting, int width) {
		return width + applyScale(easting - oEasting);
	}
	
	private int convertY(double northing, int height) {
		return height - applyScale(northing - oNorthing);
	}
	
	class MouseWheelZoomer implements MouseWheelListener {
		private static final double zoomFactor = .05;
		
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			double oldScale = scale;
			
			int rotation = e.getWheelRotation();
                        
                        
			if(rotation > 0) {
				scale /= (1 + rotation * zoomFactor);
			} else {
				scale *= (1 - rotation * zoomFactor);
			}
			// When zooming, the easting/northing at the cursor position must
			// remain the same, so we have to pan in addition to changing the
			// scale. The maths for easting (same goes for northing):
			// 
			// before: x = (easting - oEasting) * oldScale
			// after: x = (easting - newOEasting) * scale
			//
			// (x remains the same, easting remains the same)
			//
			// hence: newOEasting = easting - (easting - oEasting) * oldScale / scale
			// with: easting = x/scale + oEasting
			// hence finally: newOEasting = oEasting + x * (1/oldScale - 1/scale)
			int x = e.getX();
			int y = e.getY();
                        int w = getWidth()/2;
			int h = getHeight()/2;
			
			oEasting = oEasting + (x- w) * (1/oldScale - 1/scale);
			oNorthing = oNorthing + (h - y) * (1/oldScale - 1/scale);
			
			//System.out.println(rotation + " => " + scale);
			repaint();
		}
	}
	
	private class MousePanner implements MouseListener, MouseMotionListener {
		private int dragOriginX, dragOriginY;
		private double dragOriginOEasting, dragOriginONorthing;
		
		@Override
		public void mousePressed(MouseEvent e) {
			dragOriginX = e.getX();
			dragOriginY = e.getY();
			dragOriginOEasting = oEasting;
			dragOriginONorthing = oNorthing;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			int deltaX = e.getX() - dragOriginX;
			int deltaY = e.getY() - dragOriginY;
			
			oEasting = dragOriginOEasting - deltaX / scale;
			oNorthing = dragOriginONorthing + deltaY / scale;
			
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
		
	}
}
