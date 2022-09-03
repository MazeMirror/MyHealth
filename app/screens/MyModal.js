import React, { Component,useState } from "react";
import {View,FlatList,Text,StyleSheet,TouchableOpacity,Modal} from "react-native";
import Icon from 'react-native-vector-icons/AntDesign';

export default class MyModal extends Component {   

    
    render() {

      return (
          <View style={styles.container}>
              <Modal
              animationType="slide"
              transparent={true}
              visible={this.props.modalVisible}
              onRequestClose={() => { this.props.onDismiss() }}
              >
                  <View style={styles.centeredView}>
                      <View style={styles.modalView}>
                          <Text>Item Detail</Text>
                          <TouchableOpacity
                              onPress={() => { this.props.onDismiss() }}>
                              <Icon name="closecircleo" size={20} /> 
                          </TouchableOpacity>
                      </View>
                  </View>
              </Modal>
          </View>
      );
    }
  }
  const styles=StyleSheet.create({
    
	container: {
		flex: 1,
		backgroundColor: '#F0F3F4',
		alignItems: 'center',
		justifyContent: 'center',
	},
    centeredView: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
        marginTop: 22,
        backgroundColor: 'rgba(0, 0, 0, 0.5)'
      },

    modalView: {
        margin: 20,
        backgroundColor: "white",
        borderRadius: 20,
        padding: 35,
        flexDirection:"row",
        alignItems: "center",
        justifyContent:"space-around",
        shadowColor: "#000",
        shadowOffset: {
          width: 0,
          height: 2
        },
    }
})