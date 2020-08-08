/**
 * @author Deepak Rai
 */
package com.qa.constants;

public class CommonResponseCodes {

	private CommonResponseCodes() {
	}

	public static final int RESPONSE_CODE_200 = 200; // OK. Everything worked as expected.

	public static final int RESPONSE_CODE_201 = 201; // A resource was successfully created in response to a POST
														// request.

	public static final int RESPONSE_CODE_204 = 204; // The request was handled successfully and the response contains
														// no body content (like a DELETE request).

	public static final int RESPONSE_CODE_304 = 304; // The resource was not modified. You can use the cached version.

	public static final int RESPONSE_CODE_400 = 400; // Bad request. This could be caused by various actions by the
														// user, such as providing invalid JSON data in the request
														// body, providing invalid action parameters, etc.

	public static final int RESPONSE_CODE_401 = 401; // Authentication failed.

	public static final int RESPONSE_CODE_403 = 403; // AThe authenticated user is not allowed to access the specified
														// API end point.

	public static final int RESPONSE_CODE_404 = 404; // The requested resource does not exist.

	public static final int RESPONSE_CODE_405 = 405; // Method not allowed. Please check the Allow header for the
														// allowed HTTP methods.

	public static final int RESPONSE_CODE_415 = 415; // Unsupported media type. The requested content type or version
														// number is invalid.

	public static final int RESPONSE_CODE_422 = 422; // Data validation failed (in response to a POST request, for
														// example). Please check the response body for detailed error
														// messages.

	public static final int RESPONSE_CODE_429 = 429; // Too many requests. The request was rejected due to rate
														// limiting.

	public static final int RESPONSE_CODE_500 = 400; // Internal server error. This could be caused by internal program
														// errors.
}
