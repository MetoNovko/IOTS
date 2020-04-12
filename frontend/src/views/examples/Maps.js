/*!

=========================================================
* Argon Dashboard React - v1.1.0
=========================================================

* Product Page: https://www.creative-tim.com/product/argon-dashboard-react
* Copyright 2019 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/argon-dashboard-react/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import React from "react";
// react plugin used to create google maps
import {
    withScriptjs,
    withGoogleMap,
    GoogleMap,
    Marker
} from "react-google-maps";

// reactstrap components
import {Card, Container, Row} from "reactstrap";

// core components
import Header from "components/Headers/Header.js";
import {connect} from "react-redux";
// mapTypeId={google.maps.MapTypeId.ROADMAP}
const MapWrapper = withScriptjs(
    withGoogleMap(props => (
        <GoogleMap
            defaultZoom={12}
            defaultCenter={{lat: 41.9981, lng: 21.4254}}
            defaultOptions={{
                scrollwheel: false,
                styles: [
                    {
                        featureType: "administrative",
                        elementType: "labels.text.fill",
                        stylers: [{color: "#444444"}]
                    },
                    {
                        featureType: "landscape",
                        elementType: "all",
                        stylers: [{color: "#f2f2f2"}]
                    },
                    {
                        featureType: "poi",
                        elementType: "all",
                        stylers: [{visibility: "off"}]
                    },
                    {
                        featureType: "road",
                        elementType: "all",
                        stylers: [{saturation: -100}, {lightness: 45}]
                    },
                    {
                        featureType: "road.highway",
                        elementType: "all",
                        stylers: [{visibility: "simplified"}]
                    },
                    {
                        featureType: "road.arterial",
                        elementType: "labels.icon",
                        stylers: [{visibility: "off"}]
                    },
                    {
                        featureType: "transit",
                        elementType: "all",
                        stylers: [{visibility: "off"}]
                    },
                    {
                        featureType: "water",
                        elementType: "all",
                        stylers: [{color: "#5e72e4"}, {visibility: "on"}]
                    }
                ]
            }}
        >
            {props.devices && props.devices.map(d => {
                console.log("MAPS", d.latitude, d.longitude);
                return (
                    <Marker key={d.deviceId} position={{lat: d.latitude, lng: d.longitude}}/>
                )
            })}

        </GoogleMap>
    ))
);

class Maps extends React.Component {
    render() {
        return (
            <>
                <Header/>
                <Container className="mt--7" fluid>
                    <Row>
                        <div className="col">
                            <Card className="shadow border-0">
                                <MapWrapper
                                    googleMapURL="https://maps.googleapis.com/maps/api/js?key=AIzaSyDYUskcgL76I3vP3QTXp-8hBFS002_xC-g"
                                    loadingElement={<div style={{height: `100%`}}/>}
                                    devices={this.props.deviceState.devices}
                                    containerElement={
                                        <div
                                            style={{height: `600px`}}
                                            className="map-canvas"
                                            id="map-canvas"
                                        />
                                    }
                                    mapElement={
                                        <div style={{height: `100%`, borderRadius: "inherit"}}/>
                                    }
                                />
                            </Card>
                        </div>
                    </Row>
                </Container>
            </>
        );
    }
}

const mapStateToProps = state => ({
    ...state
});
const mapDispatchToProps = dispatch => ({});

export default connect(mapStateToProps, mapDispatchToProps)(Maps);
