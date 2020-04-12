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
// node.js library that concatenates classes (strings)
// javascipt plugin for creating charts
import Chart from "chart.js";
// react plugin used to create charts
import {Line, Bar} from "react-chartjs-2";
// reactstrap components
import {
    Card,
    CardHeader,
    CardBody,
    Container,
    Row,
    Col
} from "reactstrap";

// core components
import {
    chartOptions,
    parseOptions,
    chartExample1,
    chartExample2
} from "variables/charts.js";

import Header from "components/Headers/Header.js";
import {addDevice, deleteDevice, getDevices, getMeasurements} from "../actions/deviceActions";
import {connect} from "react-redux";

class Index extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            activeNav: 1,
            chartExample1Data: "data1"
        };
        if (window.Chart) {
            parseOptions(Chart, chartOptions());
        }
    }

    render() {
        return (
            <>
                <Header/>
                {/* Page content */}
                <Container className="mt--7" fluid>
                    <Row>
                        <Col className="mb-5 mb-xl-0" xl="8">
                            <Card className="bg-gradient-default shadow">
                                <CardHeader className="bg-transparent">
                                    <Row className="align-items-center">
                                        <div className="col">
                                            <h6 className="text-uppercase text-light ls-1 mb-1">
                                                {this.props.deviceState.deviceName}
                                            </h6>
                                            <h2 className="text-white mb-0">Measurements</h2>
                                        </div>
                                    </Row>
                                </CardHeader>
                                <CardBody>
                                    {/* Chart */}
                                    <div className="chart">
                                        <Line
                                            data={canvas => {
                                                return {
                                                    labels: this.props.deviceState.measurements?.map(m => {
                                                        const date = new Date(m.timestamp);
                                                        return date.toLocaleDateString('en-US', {
                                                            month: 'short',
                                                            day: 'numeric',
                                                            hour: 'numeric',
                                                            minute: 'numeric',
                                                            second: 'numeric'
                                                        });
                                                    }),
                                                    datasets: [
                                                        {
                                                            label: "Performance",
                                                            data: this.props.deviceState.measurements?.map(m => m.count)
                                                        }
                                                    ]
                                                };
                                            }}
                                            options={chartExample1.options}
                                            getDatasetAtEvent={e => console.log(e)}
                                        />
                                    </div>
                                </CardBody>
                            </Card>
                        </Col>
                        <Col xl="4">
                            <Card className="shadow">
                                <CardHeader className="bg-transparent">
                                    <Row className="align-items-center">
                                        <div className="col">
                                            <h6 className="text-uppercase text-muted ls-1 mb-1">
                                                {this.props.deviceState.deviceName}
                                            </h6>
                                            <h2 className="mb-0">Daily Average</h2>
                                        </div>
                                    </Row>
                                </CardHeader>
                                <CardBody>
                                    {/* Chart */}
                                    <div className="chart">
                                        <Bar
                                            data={canvas => {

                                                const data = getData(this.props.deviceState.measurements);
                                                return {
                                                    labels: data.x,
                                                    datasets: [
                                                        {
                                                            label: "Daily Average",
                                                            data: data.y
                                                        }
                                                    ]
                                                };
                                            }}
                                            options={chartExample2.options}
                                        />
                                    </div>
                                </CardBody>
                            </Card>
                        </Col>
                    </Row>
                </Container>
            </>
        );
    }
}

const getData = (measurements) => {
    const x = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'];
    const y = [0, 0, 0, 0, 0, 0, 0];
    const count = [0, 0, 0, 0, 0, 0, 0];
    //

    if (measurements != null)
        measurements.forEach(m => {
            const day = new Date(m.timestamp).getDay();
            count[day] += 1;
            y[day] += m.count;

        });

    for (let i = 0; i < 7; i++) {
        if (y[i] > 0) {
            y[i] /= count[i];
        }
    }

    return {x, y};
};

const mapStateToProps = state => ({
    ...state
});
const mapDispatchToProps = dispatch => ({
    getDevices: (username) => dispatch(getDevices(username)),
    addDevice: (newDevice) => dispatch(addDevice(newDevice)),
    deleteDevice: (deviceId) => dispatch(deleteDevice(deviceId)),
    getMeasurements: (deviceId, deviceName) => dispatch(getMeasurements(deviceId, deviceName)),
});

export default connect(mapStateToProps, mapDispatchToProps)(Index);
