package org.nypl.simplified.opds.tests.contracts;

public interface OPDSFeedParserContractType
{
  void testAcquisitionFeedFiction0()
    throws Exception;

  void testAcquisitionFeedPaginated0()
    throws Exception;

  void testDOMException()
    throws Exception;

  void testEmpty0()
    throws Exception;

  void testEntryAsFeed0()
    throws Exception;

  void testNotXMLException()
    throws Exception;

  void testParserURISyntaxException()
    throws Exception;

  void testStreamIOException()
    throws Exception;

  void testAcquisitionFeedGroups0()
    throws Exception;

  void testAcquisitionFeedCategories0()
    throws Exception;

  void testAcquisitionFeedFacets0()
    throws Exception;
}
