package com.cameronlay.comp3617.termproject.Templates;

public class Sportsfield {
    private String name;
    private String type;
    private Feature[] features;

    public String toString() {
        final StringBuilder builder;

        builder = new StringBuilder();
        builder.append("name : ").append(getName()).append("\n");
        builder.append("type : ").append(getType()).append("\n");

        for (final Feature feature : getFeatures()) {
            builder.append("  ").append(feature).append("\n");
        }

        return builder.toString();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Feature[] getFeatures() {
        return features;
    }

    public static class Feature {
        private String type;
        private Geometry geometry;
        private Properties properties;

        public String toString() {
            final StringBuilder builder;

            builder = new StringBuilder();
            builder.append("    type : ").append(type).append("\n");
            builder.append("    geometry : ").append(geometry).append("\n");
            builder.append("    properties : ").append(properties).append("\n");

            return builder.toString();
        }

        public String getType() {
            return type;
        }

        public Geometry getGeometry() {
            return geometry;
        }

        public Properties getProperties() {
            return properties;
        }

        public static class Geometry {
            private String type;
            private double[] coordinates;

            public String toString() {
                final StringBuilder builder;

                builder = new StringBuilder();
                builder.append("       type : ").append(type).append("\n");
                builder.append("       coordinates : ").
                        append(coordinates[0]).
                        append(", ").
                        append(coordinates[1]).
                        append("\n");

                return builder.toString();
            }

            public String getType() {
                return type;
            }

            public double[] getCoordinates() {
                return coordinates;
            }
        }

        public static class Properties {
            private String TYPE;
            private String PARK;
            private String ACTIVITIES;
            private String X;
            private String Y;

            public String toString() {
                final StringBuilder builder;

                builder = new StringBuilder();
                builder.append("       TYPE : ").append(TYPE).append("\n");
                builder.append("       PARK : ").append(PARK).append("\n");
                builder.append("       ACTIVITIES : ").append(ACTIVITIES).append("\n");
                builder.append("       x : ").append(X).append("\n");
                builder.append("       y : ").append(Y).append("\n");

                return builder.toString();
            }

            public String getTYPE() {
                return TYPE;
            }

            public String getPARK() {
                return PARK;
            }

            public String getACTIVITIES() {
                return ACTIVITIES;
            }

            public String getX() {
                return X;
            }

            public String getY() {
                return Y;
            }
        }
    }
}





