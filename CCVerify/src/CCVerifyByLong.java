import java.util.Scanner;

/**
 * 
 * Uses Luhn's algorithm to check to see if the provided CCN is valid or not
 * 
 * Numbers may be provided as command-line arguments, separated by spaces If no
 * numbers are provided, the user is asked for one
 * 
 * @author Nathan Lowe 2014-12-3 3:07pm
 *
 */
public class CCVerifyByLong
{

	public static void main(String[] args)
	{
		if (args.length < 1)
		{
			System.out.print("Enter a CCN to check: ");

			Scanner in = new Scanner(System.in);
			String cardNumber = in.nextLine();
			in.close();

			checkAndPrint(cardNumber);
		} else
		{
			for (int i = 0; i < args.length; i++)
			{
				checkAndPrint(args[i]);
			}
		}
	}

	/**
	 * Checks one input and prints the result of validating the input with
	 * Luhn's algorithm
	 * 
	 * @param cardNumber
	 */
	public static void checkAndPrint(String cardNumber)
	{
		String sanitizedNumber = sanitize(cardNumber);
		if (sanitizedNumber.length() != 16)
		{
			System.out.print("[WARNING: Input less than 16 digits!] ");
		}
		System.out.println(cardNumber + " is "
				+ (luhn(sanitizedNumber) ? "a VALID " : "an INVALID")
				+ " card number");

	}

	/**
	 * Strip whitespace and non-numeric characters
	 * 
	 * @param input
	 * @return The sanitized input (Whitespace and non-numeric characters
	 *         removed)
	 */
	public static String sanitize(String input)
	{
		return input.trim().replaceAll("[^0-9]", "");
	}

	/**
	 * Determines if the provided number is valid or not via Luhn's Algorithm
	 * 
	 * A general outline of the algorithm is as follows: 1) Sanitize the input
	 * number by stripping all whitespace and non-numeric characters 2) For each
	 * digit from right to left 2A) If The digit is the first digit or an in an
	 * odd numbered position Double it. If it is greater than 9, sum the
	 * individual digits 3) Add the digit to the running sum 4) Iff the final
	 * sum ends in a zero, the number is valid.
	 * 
	 * @param cardNumber
	 *            The card number to check
	 * @return Whether or not the card number is valid according to Luhn's
	 *         algorithm
	 */
	public static boolean luhn(String cardNumber)
	{
		cardNumber = sanitize(cardNumber);
		long digits = Long.parseLong(cardNumber);

		long sum = 0;
		for (int i = 0; i < cardNumber.length(); i++)
		{

			long current = pop(digits);
			digits /= 10;

			sum += (i % 2 != 0 ? sumDigits(current * 2) : current);
		}

		return sum % 10 == 0;
	}

	/**
	 * Recursively sums the constituent digits of a number
	 * 
	 * @param input
	 * @return
	 */
	public static long sumDigits(long input)
	{
		if (input < 10)
			return input;
		return pop(input) + sumDigits(input / 10);
	}

	/**
	 * Pops the rightmost digit off the end of the input
	 * 
	 * @param input
	 * @return
	 */
	public static long pop(long input)
	{
		return input % 10;
	}

}
