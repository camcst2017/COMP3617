package com.cameronlay.comp3617.termproject.Templates;

public class ArtPublic {
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
            private String email;
            private String phone;
            private String Name;
            private String Address;
            private String Descriptn;
            private String id;
            private String category;
            private String company;
            private String address2;
            private String city;
            private String prov;
            private String pcode;
            private String fax;
            private String website;
            private String social_networks;
            private String summary;
            private String catname;
            private String kml_name;
            private String X;
            private String Y;

            public String toString() {
                final StringBuilder builder;

                builder = new StringBuilder();
                builder.append("       email : ").append(email).append("\n");
                builder.append("       phone : ").append(phone).append("\n");
                builder.append("       name : ").append(Name).append("\n");
                builder.append("       address : ").append(Address).append("\n");
                builder.append("       descriptn : ").append(Descriptn).append("\n");
                builder.append("       id : ").append(id).append("\n");
                builder.append("       category : ").append(category).append("\n");
                builder.append("       company : ").append(company).append("\n");
                builder.append("       address2 : ").append(address2).append("\n");
                builder.append("       city : ").append(city).append("\n");
                builder.append("       prov : ").append(prov).append("\n");
                builder.append("       pcode : ").append(pcode).append("\n");
                builder.append("       fax : ").append(fax).append("\n");
                builder.append("       website : ").append(website).append("\n");
                builder.append("       socialNetworks : ").append(social_networks).append("\n");
                builder.append("       summary : ").append(summary).append("\n");
                builder.append("       catname : ").append(catname).append("\n");
                builder.append("       kmlName : ").append(kml_name).append("\n");
                builder.append("       x : ").append(X).append("\n");
                builder.append("       y : ").append(Y).append("\n");

                return builder.toString();
            }

            public String getEmail() {
                return email;
            }

            public String getPhone() {
                return phone;
            }

            public String getName() {
                return Name;
            }

            public String getAddress() {
                return Address;
            }

            public String getDescriptn() {
                return Descriptn;
            }

            public String getId() {
                return id;
            }

            public String getCategory() {
                return category;
            }

            public String getCompany() {
                return company;
            }

            public String getAddress2() {
                return address2;
            }

            public String getCity() {
                return city;
            }

            public String getProv() {
                return prov;
            }

            public String getPcode() {
                return pcode;
            }

            public String getFax() {
                return fax;
            }

            public String getWebsite() {
                return website;
            }

            public String getSocial_networks() {
                return social_networks;
            }

            public String getSummary() {
                return summary;
            }

            public String getCatname() {
                return catname;
            }

            public String getKml_name() {
                return kml_name;
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
