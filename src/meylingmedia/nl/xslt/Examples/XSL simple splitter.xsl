<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:n1="http://postnl/mail/operations/MailDeliveryRouteNL" version="2.0">
	<xsl:output indent="yes" />
	
	
	
	<xsl:template match="n1:ProcessMailDeliveryRouteNL/MailDeliveryRouteNL">
	
	<n1:ProcessMailDeliveryRouteNL xmlns:n1="http://postnl/mail/operations/MailDeliveryRouteNL">
	<MailDeliveryRouteNL>

	<xsl:for-each select="//MailDeliveryRoute[1]">
	<xsl:copy-of select="." />
	</xsl:for-each>
	
	</MailDeliveryRouteNL>
	</n1:ProcessMailDeliveryRouteNL>
	
	</xsl:template>
</xsl:stylesheet>	