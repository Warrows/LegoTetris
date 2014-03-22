package vision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class Processing {
	
	private static Mat img2;
	private static List<MatOfPoint> zones;
	
	private static Mat img2bw (Mat src) {
		Mat img = new Mat();
		Imgproc.cvtColor(src, img, Imgproc.COLOR_BGR2GRAY);
		Imgproc.threshold(img, img, 120, 255, Imgproc.THRESH_BINARY);
		return img;
	}
	
	private static int calculValeur (int lig, int col, int maxLig, int pasLig, int minCol, int maxCol, int pasCol, Mat img) {
		double[] val = img.get(lig, col);
		if ((int)val[0]/255 == 0) {
			return 0;
		}
		boolean vide = false;
		for (int i=lig ; i<maxLig ; i+=pasLig) {
			val = img.get(i, col);
			if ((int)val[0]/255 == 0) {
				vide = true;
				break;
			}
		}
		if (!vide) {
			return 1;
		}
		val = img.get(lig, col);
		vide = false;
		for (int i=col ; i<maxCol ; i+=pasCol) {
			val = img.get(lig, i);
			if ((int)val[0]/255 == 0) {
				vide = true;
				break;
			}
		}
		if (!vide) {
			return 1;
		}
		val = img.get(lig, col);
		vide = false;
		for (int i=col ; i>minCol ; i-=pasCol) {
			val = img.get(lig, i);
			if ((int)val[0]/255 == 0) {
				vide = true;
				break;
			}
		}
		if (!vide) {
			return 1;
		}
		return 2;
	}
	
	public static List<List<Integer>> getJeu () {
		List<List<Integer>> res = null;
		if (zones.size() > 1) {
			MatOfPoint2f zone2 = new MatOfPoint2f();
			Imgproc.approxPolyDP(new MatOfPoint2f(zones.get(1).toArray()), zone2, 4.0, true);
			double[] coins = zone2.get(0, 0);
			int minCol = (int)coins[0];
			int maxCol = minCol;
			int minLig = (int)coins[1];
			int maxLig = minLig;
			for (int i=1 ; i<zone2.rows() ; i++) {
				coins = zone2.get(i, 0);
				if ((int)coins[0] < minCol) {
					minCol = (int)coins[0];
				}
				if ((int)coins[0] > maxCol) {
					maxCol = (int)coins[0];
				}
				if ((int)coins[1] < minLig) {
					minLig = (int)coins[1];
				}
				if ((int)coins[1] > maxLig) {
					maxLig = (int)coins[1];
				}
			}

			int pasLig = (maxLig-minLig)/20;
			int pasCol = (maxCol-minCol)/10;
			if (pasLig > 0 && pasCol > 0) {
				res = new ArrayList<List<Integer>>();
				int k=0;
				for (int i=minLig ; i<maxLig ; i+=pasLig) {
					res.add(new ArrayList<Integer>());
					for (int j=minCol ; j<maxCol ; j+=pasCol) {
						int val = calculValeur(i, j, maxLig, pasLig, minCol, maxCol, pasCol, img2); 
						res.get(k).add(val);
					}
					k++;
				}
			}
			if (res != null) {
				res.remove(0);
				for (int i=0 ; i<res.size() ; i++) {
					res.get(i).remove(0);
				}
			}
		}
		return res;
	}

	public static Mat imgContours (Mat src) {
		Mat img = img2bw(src);
		img2 = img.clone();
		List<MatOfPoint> contours = new Vector<MatOfPoint>();
		Mat hierarchy = new Mat();
		Imgproc.findContours(img, contours, hierarchy, 3, 2);
		zones = new Vector<MatOfPoint>();
		List<MatOfPoint2f> zones2 = new Vector<MatOfPoint2f>();
		MatOfPoint2f poly = new MatOfPoint2f();
		for (int i=0 ; i<contours.size() ; i++) {
			Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(i).toArray()), poly, 4.0, true);
			if (poly.toList().size() == 4) {
				zones.add(contours.get(i));
				zones2.add(poly);
			}
		}
		Collections.sort(zones, new ComparaisonContour());
		for (int i=0 ; i<3 && i<zones.size() ; i++) {
			Imgproc.drawContours(src, zones, i, new Scalar(0, 255, 0), 2);
		}
		return src;
	}
}
