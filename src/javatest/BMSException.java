package javatest;

public class BMSException extends RuntimeException
{
private String info;
		
		public  BMSException(String info)
		{
			this.info=info;
		}

		public String getInfo() {
			return info;
		}

		
}
