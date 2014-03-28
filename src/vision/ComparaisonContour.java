package vision;

import java.util.Comparator;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class ComparaisonContour implements Comparator<Mat>{

	@Override
	public int compare(Mat c1, Mat c2) {
		double contourC1 = Math.abs(Imgproc.contourArea(c1));
		double contourC2 = Math.abs(Imgproc.contourArea(c2));
		return (contourC1>contourC2) ? -1 : (contourC1<contourC2) ? 1 : 0;
	}

}
