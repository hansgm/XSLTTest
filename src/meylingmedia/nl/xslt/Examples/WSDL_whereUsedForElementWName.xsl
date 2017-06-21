<xsl:transform xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <wu>
            <wsdlName>
                <xsl:value-of select="wsdl:definitions/@name"/>
            </wsdlName>
            <xsl:for-each select="//xsd:element[@name='Text']">
                <item>
                    <complextype>
                        <xsl:value-of select="ancestor::xsd:complexType/@name"/>
                    </complextype>
                    <xsl:call-template name="complexPath">
                        <xsl:with-param name="complexName" select="."/>
                        <xsl:with-param name="Path" select="''"/>
                    </xsl:call-template>
                </item>
            </xsl:for-each>
        </wu>
    </xsl:template>
    <xsl:template name="complexPath">
        <xsl:param name="complexName"/>
        <xsl:param name="path"/>
        <xsl:variable name="buildPath" select="concat(@name, '/', $path)"/>
        <xsl:for-each select="ancestor::xsd:complexType">
            <xsl:variable name="NAME" select="@name"/>
            <xsl:choose>
                <xsl:when test="//xsd:element[@type=$NAME]">
                    <xsl:for-each select="//xsd:element[@type=$NAME]">
                        <xsl:call-template name="complexPath">
                            <xsl:with-param name="complexName" select="."/>
                            <xsl:with-param name="path" select="$buildPath"/>
                        </xsl:call-template>
                    </xsl:for-each>
                </xsl:when>
                <xsl:otherwise>
                    <path>
                        <xsl:value-of select="$buildPath"/>
                    </path>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:for-each>
    </xsl:template>
</xsl:transform>
