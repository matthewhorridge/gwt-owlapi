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
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;


/**
 * Author: Matthew Horridge<br>
 * The University Of Manchester<br>
 * Bio-Health Informatics Group<br>
 * Date: 26-Oct-2006<br><br>
 */
public class OWLDatatypeImpl extends OWLObjectImpl implements OWLDatatype {


	private static final long serialVersionUID = 30402L;

	private final IRI iri;

    private final boolean top;

    private final boolean builtin;
    @SuppressWarnings("javadoc")
    public OWLDatatypeImpl( IRI iri) {
        super();
        this.iri = iri;
        top = iri.equals(OWLRDFVocabulary.RDFS_LITERAL.getIRI());
        builtin = top || OWL2Datatype.isBuiltIn(iri) || iri.equals(OWLRDFVocabulary.RDF_PLAIN_LITERAL.getIRI());
    }

    @Override
	public boolean isTopEntity() {
        return top;
    }

    @Override
	public boolean isBottomEntity() {
        return false;
    }

    /**
     * Determines if this datatype has the IRI <code>rdf:PlainLiteral</code>
     * @return <code>true</code> if this datatype has the IRI <code>rdf:PlainLiteral</code> otherwise <code>false</code>
     */
    @Override
    public boolean isRDFPlainLiteral() {
        return iri.isPlainLiteral();
    }

    /**
     * Gets the entity type for this entity
     * @return The entity type
     */
    @Override
    public EntityType<?> getEntityType() {
        return EntityType.DATATYPE;
    }

    /**
     * Gets an entity that has the same IRI as this entity but is of the specified type.
     * @param entityType The type of the entity to obtain.  This entity is not affected in any way.
     * @return An entity that has the same IRI as this entity and is of the specified type
     */
    @Override
    public <E extends OWLEntity> E getOWLEntity(EntityType<E> entityType) {
        return getOWLDataFactory().getOWLEntity(entityType, iri);
    }

    /**
     * Tests to see if this entity is of the specified type
     * @param entityType The entity type
     * @return <code>true</code> if this entity is of the specified type, otherwise <code>false</code>.
     */
    @Override
    public boolean isType(EntityType<?> entityType) {
        return getEntityType().equals(entityType);
    }

    /**
     * Returns a string representation that can be used as the ID of this entity.  This is the toString
     * representation of the IRI
     * @return A string representing the toString of the IRI of this entity.
     */
    @Override
    public String toStringID() {
        return iri.toString();
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
    public DataRangeType getDataRangeType() {
        return DataRangeType.DATATYPE;
    }

    @Override
    public OWL2Datatype getBuiltInDatatype() {
        if (!builtin) {
            throw new OWLRuntimeException("Not a built in datatype.  The getBuiltInDatatype() method should only be called on built in datatypes.");
        }
        else {
            return OWL2Datatype.getDatatype(iri);
        }
    }

    @Override
    public boolean isDouble() {
        return iri.equals(OWL2Datatype.XSD_DOUBLE.getIRI());
    }

    @Override
    public boolean isFloat() {
        return iri.equals(OWL2Datatype.XSD_FLOAT.getIRI());
    }

    @Override
    public boolean isInteger() {
        return iri.equals(OWL2Datatype.XSD_INTEGER.getIRI());
    }

    @Override
    public boolean isString() {
        return iri.equals(OWL2Datatype.XSD_STRING.getIRI());
    }

    @Override
    public boolean isBoolean() {
        return iri.equals(OWL2Datatype.XSD_BOOLEAN.getIRI());
    }

    @Override
    public boolean isDatatype() {
        return true;
    }


    @Override
    public boolean isTopDatatype() {
        return top;
    }

    @Override
	public boolean equals(Object obj) {
		if (super.equals(obj) && obj instanceof OWLDatatype) {
			return ((OWLDatatype) obj).getIRI().equals(getIRI());
		}
        return false;
    }

    @Override
    public OWLClass asOWLClass() {
        throw new OWLRuntimeException("Not an OWLClass!");
    }


    @Override
    public OWLDataProperty asOWLDataProperty() {
        throw new OWLRuntimeException("Not a data property!");
    }


    @Override
    public OWLDatatype asOWLDatatype() {
        return this;
    }


    @Override
    public OWLNamedIndividual asOWLNamedIndividual() {
        throw new OWLRuntimeException("Not an individual!");
    }


    @Override
    public OWLObjectProperty asOWLObjectProperty() {
        throw new OWLRuntimeException("Not an object property");
    }


    @Override
    public boolean isOWLClass() {
        return false;
    }


    @Override
    public boolean isOWLDataProperty() {
        return false;
    }


    @Override
    public boolean isOWLDatatype() {
        return true;
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
    public OWLAnnotationProperty asOWLAnnotationProperty() {
        throw new OWLRuntimeException("Not an annotation property");
    }

    @Override
    public boolean isOWLAnnotationProperty() {
        return false;
    }

    @Override
    public void accept(OWLEntityVisitor visitor) {
        visitor.visit(this);
    }


    @Override
    public void accept(OWLDataVisitor visitor) {
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
    public <O> O accept(OWLDataVisitorEx<O> visitor) {
        return visitor.visit(this);
    }


    @Override
    public <O> O accept(OWLObjectVisitorEx<O> visitor) {
        return visitor.visit(this);
    }

    @Override
    public void accept(OWLDataRangeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public <O> O accept(OWLDataRangeVisitorEx<O> visitor) {
        return visitor.visit(this);
    }

    @Override
	protected int compareObjectOfSameType(OWLObject object) {
        return iri.compareTo(((OWLDatatype) object).getIRI());
    }

}
