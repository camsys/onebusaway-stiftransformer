package org.onebusaway.onebusaway_stif_transformer.transformer;

public class TypedEntityMatch implements EntityMatch {
    private Class<?> _type;
    private EntityMatch _match;

    public TypedEntityMatch(Class<?> type, EntityMatch match) {
        _type = type;
        _match = match;
    }

    public Class<?> getType() {
        return _type;
    }

    public EntityMatch getPropertyMatches() {
        return _match;
    }

    public boolean isApplicableToObject(Object object) {
        return _match.isApplicableToObject(object);
    }
}