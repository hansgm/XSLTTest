<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:ns="urn:TNTPost.nl:I_R3_A2R:A2R0000_Gedeeld" version="2.0">
	<xsl:output indent="yes" />
	<xsl:template match="ns:mt_InkoopFacturen_SAP_Flat">
		<ns:mt_InkoopFacturen_SAP>

			<xsl:copy-of select="./File" />


			<xsl:for-each select="./Header">
				<xsl:variable name="header" select="." />
				<Document>
					<xsl:copy-of select="$header" />

					<xsl:for-each
						select="following-sibling::Position[preceding-sibling::Header[1] = $header]">
						<!--xsl:for-each select="following-sibling::Position" -->
						<xsl:copy-of select="." />
					</xsl:for-each>
				</Document>

				<xsl:for-each select="following-sibling::End">
					<xsl:copy-of select="." />
				</xsl:for-each>
			</xsl:for-each>

		</ns:mt_InkoopFacturen_SAP>
	</xsl:template>
</xsl:stylesheet>