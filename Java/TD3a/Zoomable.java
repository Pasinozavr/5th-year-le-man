public interface Zoomable{
	void agrandir(double facteur);
	void reduire(double facteur);
}

class IllegalZoomFactorException extends Exception{
	static final long serialVersionUID = 1L;
	 private double coeff;
	 public double  getCoeff() {
	  return coeff;
	 }
	 public IllegalZoomFactorException(String message, double coef) {
	  super(message);
	  coeff=coef;
	 }
}
