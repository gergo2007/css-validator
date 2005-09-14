//
// $Id$
// From Philippe Le Hegaret (Philippe.Le_Hegaret@sophia.inria.fr)
//
// (c) COPYRIGHT MIT and INRIA, 1997.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css1;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;

/**
 *   <H4>
 *     &nbsp;&nbsp; 'font-style'
 *   </H4>
 *   <P>
 *   <EM>Value:</EM> normal | italic | oblique<BR>
 *   <EM>Initial:</EM> normal<BR>
 *   <EM>Applies to:</EM> all elements<BR>
 *   <EM>Inherited:</EM> yes<BR>
 *   <EM>Percentage values:</EM> N/A<BR>
 *   <P> The 'font-style' property selects between normal (sometimes referred to
 *   as "roman" or "upright"), italic and oblique faces within a font family.
 *   <P> A value of 'normal' selects a font that is classified as 'normal' in
 *   the UA's font database, while 'oblique' selects a font that is labeled
 *   'oblique'.  A value of 'italic' selects a font that is labeled 'italic',
 *   or, if that is not available, one labeled 'oblique'.
 *   <P> The font that is labeled 'oblique' in the UA's font database may
 *   actually have been generated by electronically slanting a normal font.
 *   <P> Fonts with Oblique, Slanted or Incline in their names will typically be
 *   labeled 'oblique' in the UA's font database. Fonts with <EM>Italic,
 *   Cursive</EM> or <EM>Kursiv</EM> in their names will typically be labeled
 *   'italic'.
 *   <PRE>
 *   H1, H2, H3 { font-style: italic }
 *   H1 EM { font-style: normal }
 * </PRE>
 *   <P>
 *   In the example above, emphasized text within 'H1' will appear in a normal
 *   face.
 *
 * @version $Revision$
 */
public class CssFontStyleCSS2 extends CssProperty implements CssFontConstantCSS2 {

    int value;

    /**
     * Create a new CssFontStyleCSS2
     */
    public CssFontStyleCSS2() {
	// nothing to do
    }

    /**
     * Creates a new CssFontStyleCSS2
     *
     * @param expression the font style
     * @exception InvalidParamException values are incorrect
     */
    public CssFontStyleCSS2(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}

	setByUser();
	if (expression.getValue() instanceof CssIdent) {
	    int hash = ((CssIdent) expression.getValue()).hashCode();
	    for (int i=0; i<hash_values.length; i++)
		if (hash_values[i] == hash) {
		    value = i;
		    expression.next();
		    return;
		}
	}

	throw new InvalidParamException("value", expression.getValue(),
					getPropertyName(), ac);
    }

    public CssFontStyleCSS2(ApplContext ac, CssExpression expression)
	throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Returns the current value
     */
    public Object get() {
	return FONTSTYLE[value];
    }

    /**
     * Returns true if this property is "softly" inherited
     * e.g. his value equals inherit
     */
    public boolean isSoftlyInherited() {
	return value == FONTSTYLE.length - 1;
    }

    /**
     * Returns a string representation of the object.
     */
    public String toString() {
	return FONTSTYLE[value];
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "font-style";
    }

    /**
     * Add this property to the CssStyle.
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	CssFontCSS2 cssFont = ((Css1Style) style).cssFontCSS2;
	if (cssFont.fontStyle != null)
	    style.addRedefinitionWarning(ac, this);
	cssFont.fontStyle = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css1Style) style).getFontStyleCSS2();
	} else {
	    return ((Css1Style) style).cssFontCSS2.fontStyle;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssFontStyleCSS2 &&
		((CssFontStyleCSS2) property).value == value);
    }

    /**
     * Is the value of this property is a default value.
     * It is used by all macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return value == 0;
    }

    private static int[] hash_values;

    static {
	hash_values = new int[FONTSTYLE.length];
	for (int i=0; i<FONTSTYLE.length; i++)
	    hash_values[i] = FONTSTYLE[i].hashCode();
    }
}
