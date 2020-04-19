package be.codingtim.velo.ride.domain.user;

import javax.persistence.AttributeConverter;

class SubscriptionTypeAttributeConverter implements AttributeConverter<SubscriptionType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(SubscriptionType subscriptionType) {
        switch (subscriptionType) {
            case DAY:
                return 1;
            case WEEK:
                return 2;
            case YEAR:
                return 3;
            default:
                throw new IllegalStateException("No mapping specified for subscriptionType: " + subscriptionType);
        }
    }

    @Override
    public SubscriptionType convertToEntityAttribute(Integer integer) {
        switch (integer) {
            case 1:
                return SubscriptionType.DAY;
            case 2:
                return SubscriptionType.WEEK;
            case 3:
                return SubscriptionType.YEAR;
            default:
                throw new IllegalArgumentException("Unexpected value: " + integer);
        }
    }
}
