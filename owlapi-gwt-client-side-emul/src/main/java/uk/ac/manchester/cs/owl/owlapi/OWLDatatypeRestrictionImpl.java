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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


/**
 * Author: Matthew Horridge<br>
 * The University Of Manchester<br>
 * Bio-Health Informatics Group<br>
 * Date: 26-Oct-2006<br><br>
 */
public class OWLDatatypeRestrictionImpl extends OWLObjectImpl implements OWLDatatypeRestriction {


	private static final long serialVersionUID = 30402L;

	private final OWLDatatype datatype;

    private final Set<OWLFacetRestriction> facetRestrictions;

    @SuppressWarnings("javadoc")
    public OWLDatatypeRestrictionImpl(OWLDatatype datatype, Set<OWLFacetRestriction> facetRestrictions) {
        super();
        this.datatype = datatype;
        this.facetRestrictions = new HashSet<OWLFacetRestriction>(facetRestrictions);
    }

    @Override
    public DataRangeType getDataRangeType() {
        return DataRangeType.DATATYPE_RESTRICTION;
    }

    @Override
    public boolean isDatatype() {
        return false;
    }


    @Override
    public boolean isTopDatatype() {
        return false;
    }


    @Override
    public OWLDatatype asOWLDatatype() {
        throw new OWLRuntimeException("Not a data type!");
    }


    @Override
    public OWLDatatype getDatatype() {
        return datatype;
    }


    /**
     * Gets the facet restrictions on this data range
     * @return A <code>Set</code> of facet restrictions that apply to
     *         this data range
     */
    @Override
    public Set<OWLFacetRestriction> getFacetRestrictions() {
        return Collections.unmodifiableSet(facetRestrictions);
    }


    @Override
	public boolean equals(Object obj) {
        if (super.equals(obj)) {
            if (!(obj instanceof OWLDatatypeRestriction)) {
                return false;
            }
            OWLDatatypeRestriction other = (OWLDatatypeRestriction) obj;
            return other.getDatatype().equals(datatype) && other.getFacetRestrictions().equals(facetRestrictions);
        }
        return false;
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
        OWLDatatypeRestriction other = (OWLDatatypeRestriction) object;
        int diff = datatype.compareTo(other.getDatatype());
        if (diff != 0) {
            return diff;
        }
        return compareSets(facetRestrictions, other.getFacetRestrictions());
    }
}
