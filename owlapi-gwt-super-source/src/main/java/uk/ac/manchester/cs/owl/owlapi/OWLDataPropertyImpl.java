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
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

import java.util.Collections;
import java.util.Set;

/**
 * Author: Matthew Horridge<br> The University Of Manchester<br> Bio-Health Informatics Group<br> Date:
 * 26-Oct-2006<br><br>
 */
@SuppressWarnings("javadoc")
public class OWLDataPropertyImpl extends OWLPropertyExpressionImpl<OWLDataRange, OWLDataPropertyExpression> implements OWLDataProperty {

    private static final long serialVersionUID = 30406L;


    private final IRI iri;


    private final boolean builtin;


    public OWLDataPropertyImpl(IRI iri) {
        super();
        this.iri = iri;
        builtin = iri.equals(OWLRDFVocabulary.OWL_TOP_DATA_PROPERTY.getIRI()) || iri.equals(OWLRDFVocabulary.OWL_BOTTOM_DATA_PROPERTY.getIRI());
    }

    @Override
    public boolean isTopEntity() {
        return isOWLTopDataProperty();
    }

    @Override
    public boolean isBottomEntity() {
        return isOWLBottomDataProperty();
    }

    @Override
    public EntityType<?> getEntityType() {
        return EntityType.DATA_PROPERTY;
    }

    @Override
    public <E extends OWLEntity> E getOWLEntity(EntityType<E> entityType) {
        return getOWLEntity(entityType, iri);
    }

    @Override
    public boolean isType(EntityType<?> entityType) {
        return getEntityType().equals(entityType);
    }

    @Override
    public String toStringID() {
        return iri.toString();
    }

    @Override
    public boolean isDataPropertyExpression() {
        return true;
    }

    @Override
    public boolean isObjectPropertyExpression() {
        return false;
    }

    @Override
    public IRI getIRI() {
        return iri;
    }

    @Override
    public boolean isBuiltIn() {
        return builtin;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            if (!(obj instanceof OWLDataProperty)) {
                return false;
            }

            IRI otherIRI = ((OWLDataProperty) obj).getIRI();
            return otherIRI.equals(iri);
        }
        return false;
    }

    @Override
    public void accept(OWLEntityVisitor visitor) {
        visitor.visit(this);
    }


    @Override
    public void accept(OWLPropertyExpressionVisitor visitor) {
        visitor.visit(this);
    }


    @Override
    public void accept(OWLObjectVisitor visitor) {
        visitor.visit(this);
    }


    @Override
    public void accept(OWLNamedObjectVisitor visitor) {
        visitor.visit(this);
    }


    @Override
    public <O> O accept(OWLEntityVisitorEx<O> visitor) {
        return visitor.visit(this);
    }


    @Override
    public <O> O accept(OWLPropertyExpressionVisitorEx<O> visitor) {
        return visitor.visit(this);
    }


    @Override
    public <O> O accept(OWLObjectVisitorEx<O> visitor) {
        return visitor.visit(this);
    }


    @Override
    public boolean isAnonymous() {
        return false;
    }


    @Override
    public OWLDataProperty asOWLDataProperty() {
        return this;
    }


    @Override
    public OWLClass asOWLClass() {
        throw new OWLRuntimeException("Not an OWLClass!");
    }


    @Override
    public OWLDatatype asOWLDatatype() {
        throw new OWLRuntimeException("Not an OWLDatatype!");
    }


    @Override
    public OWLNamedIndividual asOWLNamedIndividual() {
        throw new OWLRuntimeException("Not an OWLIndividual!");
    }


    @Override
    public OWLObjectProperty asOWLObjectProperty() {
        throw new OWLRuntimeException("Not an OWLObjectProperty!");
    }


    @Override
    public boolean isOWLClass() {
        return false;
    }


    @Override
    public boolean isOWLDataProperty() {
        return true;
    }


    @Override
    public boolean isOWLDatatype() {
        return false;
    }


    @Override
    public boolean isOWLNamedIndividual() {
        return false;
    }


    @Override
    public boolean isOWLObjectProperty() {
        return false;
    }

    @Override
    public boolean isOWLTopObjectProperty() {
        return false;
    }

    @Override
    public boolean isOWLBottomObjectProperty() {
        return false;
    }

    @Override
    public boolean isOWLTopDataProperty() {
        return iri.equals(OWLRDFVocabulary.OWL_TOP_DATA_PROPERTY.getIRI());
    }

    @Override
    public boolean isOWLBottomDataProperty() {
        return iri.equals(OWLRDFVocabulary.OWL_BOTTOM_DATA_PROPERTY.getIRI());
    }

    @Override
    public OWLAnnotationProperty asOWLAnnotationProperty() {
        throw new OWLRuntimeException("Not an annotation property");
    }

    @Override
    public boolean isOWLAnnotationProperty() {
        return false;
    }

    @Override
    protected int compareObjectOfSameType(OWLObject object) {
        return iri.compareTo(((OWLDataProperty) object).getIRI());
    }
}
