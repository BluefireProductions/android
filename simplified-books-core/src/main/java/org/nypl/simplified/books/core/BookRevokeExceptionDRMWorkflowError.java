package org.nypl.simplified.books.core;

/**
 * An exception indicating that book revoking failed because the DRM client failed
 * when executing its workflow.
 */

public final class BookRevokeExceptionDRMWorkflowError extends BookBorrowException
{
  /**
   * Construct an exception.
   *
   * @param error_code The error code
   */

  public BookRevokeExceptionDRMWorkflowError(final String error_code)
  {
    super(error_code);
  }
}
