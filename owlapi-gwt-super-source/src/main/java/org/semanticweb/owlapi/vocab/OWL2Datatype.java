/*
 * This file is part of the OWL API.
 *
 * The contents of this file are subject to the LGPL License, Version 3.0.
 *
 * Copyright (C) 2011, The University of Manchester
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 *
 * Alternatively, the contents of this file may be used under the terms of the Apache License, Version 2.0
 * in which case, the provisions of the Apache License Version 2.0 are applicable instead of those above.
 *
 * Copyright 2011, University of Manchester
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.semanticweb.owlapi.vocab;


import java.util.*;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLRuntimeException;


/**
 * Author: Matthew Horridge<br> The University Of Manchester<br> Information Management Group<br> Date:
 * 11-Nov-2008<br><br>
 * An enumeration of the datatypes in the OWL 2 specification.  These are the datatypes in the OWL 2 datatype map.
 */
@SuppressWarnings("javadoc")
public enum OWL2Datatype {

    RDF_XML_LITERAL(Namespaces.RDF, "XMLLiteral", Category.STRING_WITHOUT_LANGUAGE_TAG, false, ".*"),

    RDFS_LITERAL(Namespaces.RDFS, "Literal", Category.UNIVERSAL, false, ".*"),

    RDF_PLAIN_LITERAL(Namespaces.RDF, "PlainLiteral", Category.STRING_WITHOUT_LANGUAGE_TAG, false, ".*"),

    OWL_REAL(Namespaces.OWL, "real", Category.NUMBER, false, ".*"),

    OWL_RATIONAL(Namespaces.OWL, "rational", Category.NUMBER, false, "(\\+|-)?([0-9]+)(\\s)*(/)(\\s)*([0-9]+)"),

    XSD_DECIMAL(XSDVocabulary.DECIMAL, Category.NUMBER, false, "(\\+|-)?([0-9]+(\\.[0-9]*)?|\\.[0-9]+)"),

    XSD_INTEGER(XSDVocabulary.INTEGER, Category.NUMBER, false, "(\\+|-)?([0-9]+)"),

    XSD_NON_NEGATIVE_INTEGER(XSDVocabulary.NON_NEGATIVE_INTEGER, Category.NUMBER, false, "((\\+)?([0-9]+))|-(0+)"),

    XSD_NON_POSITIVE_INTEGER(XSDVocabulary.NON_POSITIVE_INTEGER, Category.NUMBER, false, "-([0-9]+)|(\\+(0+))"),

    XSD_POSITIVE_INTEGER(XSDVocabulary.POSITIVE_INTEGER, Category.NUMBER, false, "(\\+)?([0-9]+)"),

    XSD_NEGATIVE_INTEGER(XSDVocabulary.NEGATIVE_INTEGER, Category.NUMBER, false, "-([0-9]+)"),

    XSD_LONG(XSDVocabulary.LONG, Category.NUMBER, true, "(\\+|-)?([0-9]+)"),

    XSD_INT(XSDVocabulary.INT, Category.NUMBER, true, "(\\+|-)?([0-9]+)"),

    XSD_SHORT(XSDVocabulary.SHORT, Category.NUMBER, true, "(\\+|-)?([0-9]+)"),

    XSD_BYTE(XSDVocabulary.BYTE, Category.NUMBER, true, "(\\+|-)?([0-9]+)"),

    XSD_UNSIGNED_LONG(XSDVocabulary.UNSIGNED_LONG, Category.NUMBER, true, "(\\+)?([0-9]+)"),

    XSD_UNSIGNED_INT(XSDVocabulary.UNSIGNED_INT, Category.NUMBER, true, "(\\+)?([0-9]+)"),

    XSD_UNSIGNED_SHORT(XSDVocabulary.UNSIGNED_SHORT, Category.NUMBER, true, "(\\+)?([0-9]+)"),

    XSD_UNSIGNED_BYTE(XSDVocabulary.UNSIGNED_BYTE, Category.NUMBER, true, "(\\+)?([0-9]+)"),

    XSD_DOUBLE(XSDVocabulary.DOUBLE, Category.NUMBER, true, "(\\+|-)?([0-9]+(\\.[0-9]*)?|\\.[0-9]+)([Ee](\\+|-)?[0-9]+)?|(\\+|-)?INF|NaN"),

    XSD_FLOAT(XSDVocabulary.FLOAT, Category.NUMBER, true, "(\\+|-)?([0-9]+(\\.[0-9]*)?|\\.[0-9]+)([Ee](\\+|-)?[0-9]+)?|(\\+|-)?INF|NaN"),

    XSD_STRING(XSDVocabulary.STRING, Category.STRING_WITHOUT_LANGUAGE_TAG, false, ".*"),

    XSD_NORMALIZED_STRING(XSDVocabulary.NORMALIZED_STRING, Category.STRING_WITHOUT_LANGUAGE_TAG, false, "([^\\r\\n\\t])*"),

    XSD_TOKEN(XSDVocabulary.TOKEN, Category.STRING_WITHOUT_LANGUAGE_TAG, false, "([^\\s])(\\s([^\\s])|([^\\s]))*"),

    XSD_LANGUAGE(XSDVocabulary.LANGUAGE, Category.STRING_WITHOUT_LANGUAGE_TAG, true, "[a-zA-Z]{1,8}(-[a-zA-Z0-9]{1,8})*"),

    XSD_NAME(XSDVocabulary.NAME, Category.STRING_WITHOUT_LANGUAGE_TAG, false, ":|[A-Z]|_|[a-z]|[\\u00C0-\\u00D6]|[\\u00D8-\\u00F6]|[\\u00F8-\\u02FF]|[\\u0370-\\u037D]|[\\u037F-\\u1FFF]|[\\u200C-\\u200D]|[\\u2070-\\u218F]|[\\u2C00-\\u2FEF]|[\\u3001-\\uD7FF]|[\\uF900-\\uFDCF]|[\\uFDF0-\\uFFFD](:|[A-Z]|_|[a-z]|[\\u00C0-\\u00D6]|[\\u00D8-\\u00F6]|[\\u00F8-\\u02FF]|[\\u0370-\\u037D]|[\\u037F-\\u1FFF]|[\\u200C-\\u200D]|[\\u2070-\\u218F]|[\\u2C00-\\u2FEF]|[\\u3001-\\uD7FF]|[\\uF900-\\uFDCF]|[\\uFDF0-\\uFFFD]|\"-\"|\".\"|[0-9]|\\u00B7|[\\u0300-\\u036F]|[\\u203F-\\u2040])*"),

    XSD_NCNAME(XSDVocabulary.NCNAME, Category.STRING_WITHOUT_LANGUAGE_TAG, false, "[A-Z]|_|[a-z]|[\\u00C0-\\u00D6]|[\\u00D8-\\u00F6]|[\\u00F8-\\u02FF]|[\\u0370-\\u037D]|[\\u037F-\\u1FFF]|[\\u200C-\\u200D]|[\\u2070-\\u218F]|[\\u2C00-\\u2FEF]|[\\u3001-\\uD7FF]|[\\uF900-\\uFDCF]|[\\uFDF0-\\uFFFD]([A-Z]|_|[a-z]|[\\u00C0-\\u00D6]|[\\u00D8-\\u00F6]|[\\u00F8-\\u02FF]|[\\u0370-\\u037D]|[\\u037F-\\u1FFF]|[\\u200C-\\u200D]|[\\u2070-\\u218F]|[\\u2C00-\\u2FEF]|[\\u3001-\\uD7FF]|[\\uF900-\\uFDCF]|[\\uFDF0-\\uFFFD]|\"-\"|\".\"|[0-9]|\\u00B7|[\\u0300-\\u036F]|[\\u203F-\\u2040])*"),

    XSD_NMTOKEN(XSDVocabulary.NMTOKEN, Category.STRING_WITHOUT_LANGUAGE_TAG, false, ".*"),

    XSD_BOOLEAN(XSDVocabulary.BOOLEAN, Category.BOOLEAN, true, "true|false|1|0"),

    XSD_HEX_BINARY(XSDVocabulary.HEX_BINARY, Category.BINARY, false, "([0-9a-fA-F]{2})*"),

    XSD_BASE_64_BINARY(XSDVocabulary.BASE_64_BINARY, Category.BINARY, false, "((([A-Za-z0-9+/] ?){4})*(([A-Za-z0-9+/] ?){3}[A-Za-z0-9+/]|([A-Za-z0-9+/] ?){2}[AEIMQUYcgkosw048] ?=|[A-Za-z0-9+/] ?[AQgw] ?= ?=))?"),

    XSD_ANY_URI(XSDVocabulary.ANY_URI, Category.URI, false, ".*"),

    XSD_DATE_TIME(XSDVocabulary.DATE_TIME, Category.TIME, false, "-?([1-9][0-9]{3,}|0[0-9]{3})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])T(([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9](\\.[0-9]+)?|(24:00:00(\\.0+)?))(Z|(\\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))?"),

    XSD_DATE_TIME_STAMP(XSDVocabulary.DATE_TIME_STAMP, Category.TIME, false, "-?([1-9][0-9]{3,}|0[0-9]{3})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])T(([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9](\\\\.[0-9]+)?|(24:00:00(\\\\.0+)?))(Z|(\\\\+|-)((0[0-9]|1[0-3]):[0-5][0-9]|14:00))");

    private static final Set<IRI> ALL_IRIS;


    static {
        Set<IRI> uris = new HashSet<IRI>();
        for (OWL2Datatype v : OWL2Datatype.values()) {
            uris.add(v.iri);
        }
        ALL_IRIS = Collections.unmodifiableSet(new TreeSet<IRI>(uris));
    }

    public static final String REG_EXP_FLAGS = "mi";

    /**
     * Gets all of the built in datatype IRIs.
     * @return A set of IRIs corresponding to the set of IRIs of all built in {@link OWL2Datatype}s.  Not {@code null}.
     */
    public static Set<IRI> getDatatypeIRIs() {
        return ALL_IRIS;
    }

    /**
     * Determines if the specified IRI identifies a built in datatype.
     * @param datatypeIRI The datatype IRI
     * @return {@code true} if the IRI identifies a built in datatype, or
     *         {@code false} if the IRI does not identify a built in datatype.
     */
    public static boolean isBuiltIn(IRI datatypeIRI) {
        return ALL_IRIS.contains(datatypeIRI);
    }


    /**
     * Given an IRI that identifies an {@link org.semanticweb.owlapi.model.OWLDatatype}, this method obtains the
     * corresponding {@link OWL2Datatype}.
     * @param datatype The datatype IRI.  Not {@code null}.
     * @return The {@link OWL2Datatype} that has the specified {@link IRI}.
     * @throws OWLRuntimeException if the specified IRI is not a built in datatype IRI.
     */
    public static OWL2Datatype getDatatype(IRI datatype) {
        if (!isBuiltIn(datatype)) {
            throw new OWLRuntimeException(datatype + " is not a built in datatype!");
        }
        for (OWL2Datatype v : values()) {
            if (v.iri.equals(datatype)) {
                return v;
            }
        }
        throw new OWLRuntimeException(datatype + " is not a built in datatype!");
    }

    private final String shortName;

    private final IRI iri;

    private final Category category;

    private final boolean finite;

    private RegExp pattern;

    OWL2Datatype(Namespaces namespace, String shortName, Category category, boolean finite, String regEx) {
        iri = IRI.create(namespace.toString(), shortName);
        this.shortName = shortName;
        this.category = category;
        this.finite = finite;
        if (regEx != null) {
            pattern = RegExp.compile(regEx, REG_EXP_FLAGS);
        }
    }

    OWL2Datatype(XSDVocabulary xsd, Category category, boolean finite, String regEx) {
        iri = xsd.getIRI();
        shortName = xsd.getShortName();
        this.category = category;
        this.finite = finite;
        pattern = RegExp.compile(regEx, REG_EXP_FLAGS);
    }


    /**
     * Gets the short human readable name for this datatype
     * @return The short human readable name
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Gets the IRI of this datatype
     * @return The IRI of this datatype
     */
    public IRI getIRI() {
        return iri;
    }

    /**
     * Gets the category for this datatype
     * @return The category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Determines if this datatype is a numeric datatype
     * @return {@code true} if this datatype is a numeric datatype
     */
    public boolean isNumeric() {
        return category.equals(Category.NUMBER);
    }

    /**
     * Determines whether or not this datatype is finite.
     * @return {@code true} if this datatype is finite, or
     *         {@code false} if this datatype is infinite.
     */
    public boolean isFinite() {
        return finite;
    }


    /**
     * Gets the facets that are allowed for facet restrictions of this
     * datatype
     * @return The allowed facets
     */
    public Collection<OWLFacet> getFacets() {
        return category.getFacets();
    }


    /**
     * Gets the equivalent OWLDatatype from the given factory.
     * @param factory the OWLDataFactory.  Not {@code null}.
     * @return An {@link org.semanticweb.owlapi.model.OWLDatatype} that has the same IRI as this {@link OWL2Datatype}.  Not {@code null}.
     */
    public OWLDatatype getDatatype(OWLDataFactory factory){
        return factory.getOWLDatatype( getIRI() );
    }

    /**
     * Determines if the specified string is the lexical space of this datatype
     * @param s The string to test
     * @return {@code true} if the string is in the lexical space, otherwise {@code false}
     */
    public boolean isInLexicalSpace(String s) {
        MatchResult result = pattern.exec(s);
        if(result == null) {
            return false;
        }
        String group = result.getGroup(0);
        return group.length() == s.length();
    }

    public enum Category {

        UNIVERSAL("Universal literal"),

        NUMBER("Number", OWLFacet.MIN_INCLUSIVE, OWLFacet.MAX_INCLUSIVE, OWLFacet.MIN_EXCLUSIVE, OWLFacet.MAX_EXCLUSIVE),

        STRING_WITH_LANGUAGE_TAG("String with a language tag", OWLFacet.MIN_LENGTH, OWLFacet.MAX_LENGTH, OWLFacet.LENGTH, OWLFacet.PATTERN, OWLFacet.LANG_RANGE),

        STRING_WITHOUT_LANGUAGE_TAG("String without a language tag", OWLFacet.MIN_LENGTH, OWLFacet.MAX_LENGTH, OWLFacet.LENGTH, OWLFacet.PATTERN),

        BOOLEAN("Boolean value"),

        BINARY("Binary data", OWLFacet.MIN_LENGTH, OWLFacet.MAX_LENGTH, OWLFacet.LENGTH),

        URI("URI", OWLFacet.MIN_LENGTH, OWLFacet.MAX_LENGTH, OWLFacet.PATTERN),

        TIME("Time instant", OWLFacet.MIN_INCLUSIVE, OWLFacet.MAX_INCLUSIVE, OWLFacet.MIN_EXCLUSIVE, OWLFacet.MAX_EXCLUSIVE);

        private final String name;

        private final Set<OWLFacet> facets;


        Category(String name, OWLFacet... facets) {
            this.name = name;
            Set<OWLFacet> f = new HashSet<OWLFacet>(Arrays.asList(facets));
            this.facets = Collections.unmodifiableSet(f);
        }


        public String getName() {
            return name;
        }


        public Set<OWLFacet> getFacets() {
            return facets;
        }
    }

    public enum WhiteSpaceNormalisation {

        /**
         * No normalization is done, the value is not changed
         * (this is the behavior required by [XML] for element content)
         */
        PRESERVE,

        /**
         * All occurrences of #x9 (tab), #xA (line feed) and #xD (carriage return)
         * are replaced with #x20 (space)
         */
        REPLACE,

        /**
         * After the processing implied by replace, contiguous sequences of #x20's are collapsed
         * to a single #x20, and any #x20 at the start or end of the string is then removed.
         */
        COLLAPSE;

        /**
         * Gets the normalised version of a string
         * @param s The string to normalise
         * @return The normalised string
         */
        public String getNormalisedString(String s) {
            switch (this) {
                case REPLACE:
                    return s.replaceAll("\\t|\\n|\\r", " ");
                case COLLAPSE:
                    return REPLACE.getNormalisedString(s).replaceAll("\\s+", " ").trim();
                case PRESERVE:
                default:
                    return s;
            }
        }
    }
}