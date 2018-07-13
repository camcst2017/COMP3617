package com.cameronlay.comp3617.termproject.Templates;

public class Literacy {
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
            private String Name;
            private String Description;
            private String Category;
            private String Hours;
            private String Location;
            private String PC;
            private String Phone;
            private String Email;
            private String Website;
            private String X;
            private String Y;

            public String toString() {
                final StringBuilder builder;

                builder = new StringBuilder();
                builder.append("       Name : ").append(Name).append("\n");
                builder.append("       Description : ").append(Description).append("\n");
                builder.append("       Category : ").append(Category).append("\n");
                builder.append("       Hours : ").append(Hours).append("\n");
                builder.append("       Location : ").append(Location).append("\n");
                builder.append("       PC : ").append(PC).append("\n");
                builder.append("       Phone : ").append(Phone).append("\n");
                builder.append("       Email : ").append(Email).append("\n");
                builder.append("       Website : ").append(Website).append("\n");
                builder.append("       x : ").append(X).append("\n");
                builder.append("       y : ").append(Y).append("\n");

                return builder.toString();
            }

            public String getEmail() {
                return Email;
            }

            public String getPhone() {
                return Phone;
            }

            public String getName() {
                return Name;
            }

            public String getLocation() {
                return Location;
            }

            public String getDescription() {
                return Description;
            }

            public String getHours() {
                return Hours;
            }

            public String getCategory() {
                return Category;
            }

            public String getPC() {
                return PC;
            }

            public String getWebsite() {
                return Website;
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




