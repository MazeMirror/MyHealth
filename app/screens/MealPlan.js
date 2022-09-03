import React, { Component,useState } from "react";
import {View,FlatList,Text,StyleSheet,TouchableOpacity,Modal} from "react-native";
import Icon from 'react-native-vector-icons/AntDesign';
import MyModal from "./MyModal";

export default class MealPlan extends Component{

    constructor(props){
        super(props)
        this.state={
            isLoading:true,
            dataSource:[],
            isModalVisible:false,
            selectedItem:null
        }
    }

    componentDidMount(){
        fetch('http://localhost:8383/api/mealPlan')
        .then((response)=>response.json())
        .then((responseJson)=>{
            this.setState({
                isLoading:false,
                dataSource:responseJson
                
            })
        })
    }

    _hideMyModal = () => {
        this.setState({isModalVisible: false})
    }

    _showModal = (item) => this.setState({ isModalVisible: true, 
    selectedItem: item })

    _renderItem=({item,index})=>{
        
        return(

            <View style={{flexDirection:"row",alignItems: 'center',justifyContent: 'space-around'}}>
            
            <TouchableOpacity onPress={this._showModal}
             style={{marginVertical:10,alignItems: 'center',justifyContent: 'space-between',flexDirection:"row",backgroundColor: "#CEE5EE", padding: 10,width:"80%",borderRadius:6}}>
                <Text style={{fontWeight:"bold"}} >{item.name}</Text>
                <Icon name="right"/>
            </TouchableOpacity>
        </View>

        )
        
    }

    render(){
        let {dataSource,isLoading}=this.state

        return(

            <View style={styles.container}>
                <Text style={{fontSize:20,marginTop:"15%", fontWeight:"bold"}}> Mis Planes Alimenticios </Text>
                <View style={{marginTop:20}}>
                    <Text style={{fontSize:18,padding:20, fontWeight:"bold"}}> Planes Alimenticios </Text>

    
                    <FlatList
                        data={dataSource}
                        renderItem={this._renderItem}
                        keyExtractor={(item,index)=>index.toString()}
                    />

                    <MyModal 
                    modalVisible={this.state.isModalVisible} 
                    selectedItem={this.state.selectedItem}
                    onDismiss={this._hideMyModal} />
                    
                </View>

                
            </View>
        )
    }

}

const styles=StyleSheet.create({
    container:{
        flex:1,
        padding:20,
    },


})


