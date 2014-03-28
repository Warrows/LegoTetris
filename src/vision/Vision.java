package vision;

import java.util.List;

import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

import com.atul.JavaOpenCV.Imshow;

public class Vision {

	public static void show () {
		System.loadLibrary("opencv_java247");
        VideoCapture cap = new VideoCapture(1);
        if(!cap.isOpened())
        	return;
        Imshow im = new Imshow("Detection");
        Mat img = new Mat();
        while (true) {
        	if (cap.read(img)) {
        		img = Processing.imgContours(img);
        		im.showImage(img);
        		
        		List<List<Integer>> etatJeu = Processing.getJeu();
        		if (etatJeu != null) {
        			for (int i=0 ; i<etatJeu.size() ; i++) {
        				for (int j=0 ; j<etatJeu.get(i).size() ; j++) {
        					System.out.print(etatJeu.get(i).get(j));
        				}
        				System.out.println();
        			}
        			System.out.println();
        		}
        		
        	}
        }
	}
}
