import React from "react";
import {connect} from "react-redux";
import {addDevice, getMeasurements} from "../../actions/deviceActions";
import {getDevices} from "../../actions/deviceActions";
import {deleteDevice} from "../../actions/deviceActions";
import {Button} from "reactstrap";
/* reactstrap components*/
import {Card, CardBody, CardTitle, Container, Row, Col} from "reactstrap";
import Modal from "reactstrap/es/Modal";
import CardHeader from "reactstrap/es/CardHeader";
import Form from "reactstrap/es/Form";
import InputGroupText from "reactstrap/es/InputGroupText";
import InputGroup from "reactstrap/es/InputGroup";
import InputGroupAddon from "reactstrap/es/InputGroupAddon";
import FormGroup from "reactstrap/es/FormGroup";
import Input from "reactstrap/es/Input";

class Header extends React.Component {
    state = {
        formModal: false,
        model: "",
        description: "",
        isPublic: false,
        longitude: "",
        latitude: "",
        devices: []
    };
    toggleModal = state => {
        this.setState({
            [state]: !this.state[state]
        });
    };

    componentDidMount() {
        this.props.getDevices(this.props.authState.user.sub);
        //this.setState({ devices: this.props.deviceState.devices })
    }

    componentWillReceiveProps(nextProps, nextContext) {

    }

    onChange = (stateName, value) => {
        this.setState({
            [stateName]: value
        });
    };
    refreshComponent = () => {
        this.props.getDevices(this.props.authState.user.sub);
    };
    // componentWillReceiveProps(nextProps, nextContext) {
    //   if (nextProps.errors) {
    //     this.setState({ devices: nextProps.deviceState.devices });
    //   }
    // }

    render() {
        return (
            <>
                <div className="header bg-gradient-info pb-8 pt-5 pt-md-8">
                    <Container fluid>
                        <Row>
                            <Col lg="6" xl="3">
                                <Button
                                    color="primary"
                                    onClick={() =>
                                        this.props.addDevice(
                                            {
                                                "model": "geiger Counter",
                                                "description": "for measuring radiation",
                                                "isPublic": false,
                                                "latitude": 42.004816,
                                                "longitude": 21.409933,
                                                "lastMeasurement": {}
                                            }, this.props.history
                                        ).then(setTimeout(() => this.refreshComponent(), 1000))//setTimeout(this.props.getDevices(this.props.authState.user.sub), 1500))//NEEDS FIX!!!
                                    }
                                >
                                    Geiger Counter
                                </Button>
                                {/*MODAL START*******************/}
                                <Button
                                    //block
                                    color="default"
                                    type="button"
                                    onClick={() => this.toggleModal("formModal")}
                                >
                                    Add Device
                                </Button>
                                <Modal
                                    className="modal-dialog-centered"
                                    size="sm"
                                    isOpen={this.state.formModal}
                                    toggle={() => this.toggleModal("formModal")}
                                >
                                    <div className="modal-body p-0">
                                        <Card className="bg-secondary shadow border-0">
                                            <CardHeader className="bg-transparent pb-5">
                                                <div className="text-muted text-center mt-2 mb-0">
                                                    <small>Add new device</small>
                                                </div>
                                            </CardHeader>
                                            <CardBody className="px-lg-5 py-lg-5">
                                                <div className="text-center text-muted mb-4">
                                                    <small>Enter information</small>
                                                </div>
                                                <Form role="form">
                                                    <FormGroup className="mb-3">
                                                        <InputGroup className="input-group-alternative">
                                                            <InputGroupAddon addonType="prepend">
                                                                <InputGroupText>
                                                                    <i className="ni ni-paper-diploma"/>
                                                                </InputGroupText>
                                                            </InputGroupAddon>
                                                            <Input placeholder="Model" type="text"
                                                                   onChange={e => this.onChange("model", e.target.value)}/>
                                                        </InputGroup>
                                                    </FormGroup>
                                                    <FormGroup className="mb-3">
                                                        <InputGroup className="input-group-alternative">
                                                            <InputGroupAddon addonType="prepend">
                                                                <InputGroupText>
                                                                    <i className="ni ni-paper-diploma"/>
                                                                </InputGroupText>
                                                            </InputGroupAddon>
                                                            <Input placeholder="Description" type="text"
                                                                   onChange={e => this.onChange("description", e.target.value)}/>
                                                        </InputGroup>
                                                    </FormGroup>
                                                    <FormGroup className="mb-3">
                                                        <InputGroup className="input-group-alternative">
                                                            <InputGroupAddon addonType="prepend">
                                                                <InputGroupText>
                                                                    <i className="ni ni-square-pin"/>
                                                                </InputGroupText>
                                                            </InputGroupAddon>
                                                            <Input placeholder="Longitude" type="number"
                                                                   onChange={e => this.onChange("longitude", e.target.value)}/>
                                                        </InputGroup>
                                                    </FormGroup>
                                                    <FormGroup className="mb-3">
                                                        <InputGroup className="input-group-alternative">
                                                            <InputGroupAddon addonType="prepend">
                                                                <InputGroupText>
                                                                    <i className="ni ni-square-pin"/>
                                                                </InputGroupText>
                                                            </InputGroupAddon>
                                                            <Input placeholder="Latitude" type="number"
                                                                   onChange={e => this.onChange("latitude", e.target.value)}/>
                                                        </InputGroup>
                                                    </FormGroup>
                                                    <div
                                                        className="custom-control custom-control-alternative custom-checkbox">
                                                        <input
                                                            className="custom-control-input"
                                                            id=" isPublic"
                                                            type="checkbox"
                                                            onChange={e => this.onChange("isPublic", (e.target.value === "on"))}
                                                        />
                                                        <label
                                                            className="custom-control-label"
                                                            htmlFor=" isPublic"
                                                        >
                                                            <span className="text-muted">Public Device </span><i
                                                            className="ni ni-world-2"/>
                                                        </label>
                                                    </div>
                                                    <div className="text-center">
                                                        <Button
                                                            className="my-4"
                                                            color="primary"
                                                            type="button"
                                                            onClick={() =>
                                                                this.props.addDevice(
                                                                    {
                                                                        model: this.state.model,
                                                                        description: this.state.description,
                                                                        isPublic: this.state.isPublic,
                                                                        longitude: this.state.longitude,
                                                                        latitude: this.state.latitude,
                                                                        lastMeasurement: {}
                                                                    }, this.props.history
                                                                ).then(setTimeout(() => this.refreshComponent(), 1000)).then(() => this.toggleModal("formModal"))//)//NEEDS FIX!!!
                                                            }
                                                        >
                                                            Add device
                                                        </Button>
                                                    </div>
                                                </Form>
                                            </CardBody>
                                        </Card>
                                    </div>
                                </Modal>
                                {/*MODAL END ***********/}
                            </Col>
                        </Row>
                    </Container>
                    <br/>
                    <Container fluid>
                        <div className="header-body">
                            {/* Card stats */}
                            <Row>
                                {// we first verify if the deviceState is undefined
                                    this.props.deviceState &&
                                    // then verify if the deviceState.devices is
                                    // populated with devices from our database
                                    this.props.deviceState.devices &&
                                    // and lastly, we render them using the map function
                                    this.props.deviceState.devices.map((prop, key) => {
                                        return (
                                            <Col lg="6" xl="3" key={key}>
                                                <Card className="card-stats mb-4 mb-xl-0"
                                                      onClick={() => this.props.getMeasurements(prop.deviceId, prop.model)}>
                                                    <CardBody>
                                                        <Row>
                                                            <div className="col">
                                                                <CardTitle
                                                                    tag="h5"
                                                                    className="text-uppercase text-muted mb-0"
                                                                >
                                                                    {prop.model}
                                                                </CardTitle>
                                                                <span className="h2 font-weight-bold mb-0">
                                  {prop.description}
                                </span>
                                                            </div>
                                                            <Col className="col-auto">
                                                                {prop.public ?
                                                                    <i className="ni ni-world-2 mr-2" title="Public"/> :
                                                                    <i className="ni ni-lock-circle-open mr-2"
                                                                       title="Private"/>}
                                                                <div
                                                                    className={
                                                                        "icon icon-shape bg-info text-white rounded-circle shadow" //+ prop.statIconColor
                                                                    }
                                                                >
                                                                    <i className="fas fa-ruler-vertical"
                                                                       title="Device"/*{prop.statIcon}*/ />
                                                                </div>
                                                            </Col>
                                                        </Row>
                                                        <p className="mt-3 mb-0 text-muted text-sm">
                              <span
                                  className={"mr-2 " /*+ prop.statFooterIconState*/}
                              >
                                <i className="fas fa-bullseye mr-1"/*{prop.statFooterIcon}*/ />{" "}
                                  {prop.latitude}, {prop.longitude}
                              </span>{" "}
                                                            <p className="mt-3 mb-0 text-sm">
                                                                <span className="text-success mr-2">
                                                                    <i className="fa fa-history"></i> {prop.lastMeasurement?.count}</span>
                                                                <span
                                                                    className="text-nowrap">{prop.lastMeasurement ? "" : "No"} last measurement</span>
                                                            </p>
                                                            <span className="text-nowrap float-right">

                                  <button
                                      className="btn btn-danger ml-4 mt-0"
                                      onClick={() => this.props.deleteDevice(prop.deviceId)}
                                  >
                                    Delete
                                  </button>
                              </span>
                                                        </p>
                                                    </CardBody>
                                                </Card>
                                            </Col>
                                        );
                                    })}
                            </Row>
                        </div>
                    </Container>
                </div>
            </>
        );
    }
}

const mapStateToProps = state => ({
    ...state
});
const mapDispatchToProps = dispatch => ({
    getDevices: (username) => dispatch(getDevices(username)),
    addDevice: (newDevice, history) => dispatch(addDevice(newDevice)),
    getMeasurements: (deviceId, deviceName) => dispatch(getMeasurements(deviceId, deviceName)),
    deleteDevice: (deviceId) => dispatch(deleteDevice(deviceId))
});

export default connect(mapStateToProps, mapDispatchToProps)(Header);
