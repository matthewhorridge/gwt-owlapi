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
package uk.ac.manchester.cs.owl.owlapi;

import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.XSDVocabulary;

import java.io.Serializable;

/**
 * @author ignazio no cache used
 */
public class InternalsNoCache implements OWLDataFactoryInternals, Serializable {

    private static final long serialVersionUID = 30402L;

    private static final OWLDatatype RDF_PLAIN_LITERAL = OWL2DatatypeImpl.getDatatype(OWL2Datatype.RDF_PLAIN_LITERAL);

    private static final OWLDatatype XSD_BOOLEAN = OWL2DatatypeImpl.getDatatype(OWL2Datatype.XSD_BOOLEAN);

    private static final OWLDatatype XSD_DOUBLE = OWL2DatatypeImpl.getDatatype(OWL2Datatype.XSD_DOUBLE);

    private static final OWLDatatype XSD_FLOAT = OWL2DatatypeImpl.getDatatype(OWL2Datatype.XSD_FLOAT);

    private static final OWLDatatype XSD_INTEGER = OWL2DatatypeImpl.getDatatype(OWL2Datatype.XSD_INTEGER);

    private static final OWLDatatype RDFS_LITERAL = OWL2DatatypeImpl.getDatatype(OWL2Datatype.RDFS_LITERAL);

    private final OWLDataFactory factory;

    private final OWLLiteral trueLiteral;

    private final OWLLiteral falseLiteral;


    /**
     * @param f the factory to refer to
     * @param useCompression true if compression of literals should be used
     */
    public InternalsNoCache(OWLDataFactory f, boolean useCompression) {
        factory = f;
        trueLiteral = new OWLLiteralImplBoolean(true);
        falseLiteral = new OWLLiteralImplBoolean(false);
    }

    @Override
    public void purge() {
    }

    @Override
    public OWLClass getOWLClass(IRI iri) {
        return new OWLClassImpl(iri);
    }

    @Override
    public OWLObjectProperty getOWLObjectProperty(IRI iri) {
        return new OWLObjectPropertyImpl(iri);
    }

    @Override
    public OWLDataProperty getOWLDataProperty(IRI iri) {
        return new OWLDataPropertyImpl(iri);
    }

    @Override
    public OWLNamedIndividual getOWLNamedIndividual(IRI iri) {
        return new OWLNamedIndividualImpl(iri);
    }

    @Override
    public OWLDatatype getOWLDatatype(IRI iri) {
        return new OWLDatatypeImpl(iri);
    }

    @Override
    public OWLAnnotationProperty getOWLAnnotationProperty(IRI iri) {
        return new OWLAnnotationPropertyImpl(iri);
    }

    @Override
    public OWLLiteral getOWLLiteral(float value) {
        return new OWLLiteralImplFloat(value, getFloatOWLDatatype());
    }

    @Override
    public OWLLiteral getOWLLiteral(String value) {
        return new OWLLiteralImplNoCompression(value, "", getOWLDatatype(XSDVocabulary.STRING.getIRI()));
    }

    @Override
    public OWLLiteral getOWLLiteral(String literal, String lang) {
        String normalisedLang;
        if (lang == null) {
            normalisedLang = "";
        }
        else {
            // TODO: NOT ALLOWED ON CLIENT
            normalisedLang = lang.trim();//.toLowerCase(Locale.ENGLISH);
        }
        return new OWLLiteralImplNoCompression(literal, normalisedLang, null);
    }

    @Override
    public OWLLiteral getOWLLiteral(int value) {
        return new OWLLiteralImplInteger(value, getIntegerOWLDatatype());
    }

    @Override
    public OWLLiteral getOWLLiteral(boolean value) {
        return value ? trueLiteral : falseLiteral;
    }

    @Override
    public OWLLiteral getOWLLiteral(double value) {
        return new OWLLiteralImplDouble(value, getDoubleOWLDatatype());
    }

    @Override
    public OWLLiteral getOWLLiteral(String lexicalValue, OWLDatatype datatype) {
        OWLLiteral literal;
        if (datatype.isRDFPlainLiteral()) {
            int sep = lexicalValue.lastIndexOf('@');
            if (sep != -1) {
                String lex = lexicalValue.substring(0, sep);
                String lang = lexicalValue.substring(sep + 1);
                literal = new OWLLiteralImplNoCompression(lex, lang, getRDFPlainLiteral());
            }
            else {
                    literal = new OWLLiteralImplNoCompression(lexicalValue, "", datatype);

            }
        }
        else {
            // check the four special cases
            try {
                if (datatype.isBoolean()) {
                    lexicalValue = lexicalValue.trim();
                    if (isBooleanTrueValue(lexicalValue)) {
                        literal = getOWLLiteral(true);
                    }
                    else if (isBooleanFalseValue(lexicalValue)) {
                        literal = getOWLLiteral(false);
                    }
                    else {
                        literal = factory.getOWLLiteral(Boolean.parseBoolean(lexicalValue));
                    }
                }
                else if (datatype.isFloat()) {
                    float f;
                    try {
                        f = Float.parseFloat(lexicalValue);
                        literal = getOWLLiteral(f);
                    }
                    catch (NumberFormatException e) {
                            literal = new OWLLiteralImplNoCompression(lexicalValue, "", datatype);
                    }
                }
                else if (datatype.isDouble()) {
                    literal = getOWLLiteral(Double.parseDouble(lexicalValue));
                }
                else if (datatype.isInteger()) {
                    literal = getOWLLiteral(Integer.parseInt(lexicalValue));
                }
                else {
                        literal = new OWLLiteralImplNoCompression(lexicalValue, "", datatype);
                }
            }
            catch (NumberFormatException e) {
                // some literal is malformed, i.e., wrong format
                    literal = new OWLLiteralImplNoCompression(lexicalValue, "", datatype);
            }
        }
        return literal;
    }

    private boolean isBooleanFalseValue(String lexicalValue) {
        return lexicalValue.equals("0") || lexicalValue.equals("false");
    }

    private boolean isBooleanTrueValue(String lexicalValue) {
        return lexicalValue.equals("1") || lexicalValue.equals("true");
    }

    @Override
    public OWLDatatype getTopDatatype() {
        return RDFS_LITERAL;
    }

    @Override
    public OWLDatatype getIntegerOWLDatatype() {
        return XSD_INTEGER;
    }

    @Override
    public OWLDatatype getFloatOWLDatatype() {
        return XSD_FLOAT;
    }

    @Override
    public OWLDatatype getDoubleOWLDatatype() {
        return XSD_DOUBLE;
    }

    @Override
    public OWLDatatype getBooleanOWLDatatype() {
        return XSD_BOOLEAN;
    }

    @Override
    public OWLDatatype getRDFPlainLiteral() {
        return RDF_PLAIN_LITERAL;
    }
}
