package mycodelearn.undergrowth.book.mw.refactoring.code;

/**
 * 
* Description: TODO(用于电影价格 不同电影类型提供不同的实现)
* @author <a href="undergrowth@126.com">undergrowth</a>
* @date 2016年7月8日
* @version 1.0.0
 */
public abstract class  MoviePrice {

	public  abstract double getPrice(int rentalDay);
	
	public int getFrequencyRental(int rentalDay) {
		if (this instanceof NewReleaseMoviePrice && rentalDay > 1)
			return 2;
		return 1;
	}
}
