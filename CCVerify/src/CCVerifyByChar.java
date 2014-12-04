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
public class CCVerifyByChar
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
	 * number by stripping all whitespace and non-numeric characters 2) Reverse
	 * the input string 3) For each individual digit 3A) If the digit is in an
	 * odd position, double it. If the resultant number is greater than nine,
	 * instead store the sum of it's constituent digits 4) Sum each of the
	 * digits 5) Iff the sum ends in a zero, then it is a valid number.
	 * 
	 * @param cardNumber
	 *            The card number to check
	 * @return Whether or not the card number is valid according to Luhn's
	 *         algorithm
	 */
	public static boolean luhn(String cardNumber)
	{
		String reversed = new StringBuilder(sanitize(cardNumber)).reverse()
				.toString();

		int[] digits = digitsOf(reversed);
		for (int i = 0; i < digits.length; i++)
		{
			if (i % 2 != 0)
			{
				digits[i] *= 2;
				if (digits[i] > 9)
				{
					digits[i] = sum(digitsOf(String.valueOf(digits[i])));
				}
			}
		}

		return sum(digits) % 10 == 0;
	}

	/**
	 * Splits a string of numbers into its individual digits NOTE: This method
	 * will strip any non-numeric character from the input string
	 * 
	 * @param input
	 *            A string of numbers
	 * @return an array of digits extracted from the string
	 */
	public static int[] digitsOf(String input)
	{
		input = sanitize(input);
		int[] result = new int[input.length()];

		for (int i = 0; i < input.length(); i++)
		{
			result[i] = Character.digit(input.charAt(i), 10);
		}

		return result;
	}

	/**
	 * Compute the sum of an array of integers
	 * 
	 * @param numbers
	 *            An array of integers to sum
	 * @return the sum of those integers
	 */
	public static int sum(int... numbers)
	{
		int result = 0;
		for (int i : numbers)
		{
			result += i;
		}

		return result;
	}
}
