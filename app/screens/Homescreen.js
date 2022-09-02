import React, {useState} from "react";
import {View,Text,StyleSheet,TouchableOpacity, Button,StatusBar,Modal,Pressable} from "react-native";
import {useNavigation} from "@react-navigation/native"
import CircularProgress from 'react-native-circular-progress-indicator';
import Icon from 'react-native-vector-icons/Feather';
import moment from "moment/moment";
import { formatDistance, subDays, startOfWeek, endOfWeek,format} from 'date-fns';
import { es } from 'date-fns/locale/es'

const HomeScreen=()=>{

    const navigation=useNavigation();

    const [modalVisible, setModalVisible] = useState(false);

/* moment.locale();      

var startOfWeeks = moment().startOf('week').toDate();
var endOfWeeks   = moment().endOf('week').toDate();

startOfWeeks=moment().format('ll');
endOfWeeks=moment().format('ll');

console.log(startOfWeeks);
console.log(endOfWeeks); */

    const start = startOfWeek(new Date(), {weekStartsOn: 1});
    const end = endOfWeek(new Date(), {weekStartsOn: 1});

    const startWeek = format(start, 'd MMMM', { locale: es });
    const endWeek = format(end, "d MMMM", { locale: es });

    console.log(startWeek);
    console.log(endWeek);


    return(
        
        <View style={styles.container}>
			
            <Modal transparent={true} visible={modalVisible} backdropOpacity= {1}
            onRequestClose={() => { Alert.alert("Modal has been closed."); setModalVisible(!modalVisible);}} >
                <View style={styles.centeredView}>
                <View style={styles.modalView}>
                    <Text style={{fontWeight:"bold"}}>NO TIENES UN SMARTWATCH VINCULADO</Text>
                    <Icon name="watch" size={50} color="#2F6A88" style={{margin:20}}/>
                    <Text style={{textAlign:"center"}}>Para disfrutar toda la experiencia de la aplicación, vincula un nuevo dispositivo ahora.</Text>
                    <Pressable
                    style={{backgroundColor:"#FF7E10", padding: 10,borderRadius:10,marginTop:20}}
                    onPress={() => setModalVisible(!modalVisible)}
                    >
                   

                    <Text style={{color:"#fff"}}>Omitir por ahora</Text>
                    </Pressable>
                </View>
                </View>
            </Modal>

			<TouchableOpacity onPress={() => setModalVisible(true)} style={{flexDirection:"row", alignItems: "center", justifyContent:"center",backgroundColor: "#FF7E10", padding: 10,width:"90%"}}>
				<Icon name="watch" size={30} color="#fff" />
				<Text style={{color:"white",marginLeft:20}}>Registrar nuevo Smartwatch</Text>
      		</TouchableOpacity>
			<View style={styles.smallContainer}>
				<Text style={{marginTop:20,fontWeight:"bold",marginBottom:20}}> Tu progreso de hoy</Text>
				<View style={{flexDirection:"row",alignItems: 'center',justifyContent: 'space-around'}}>
					<View style={{alignItems: 'center',justifyContent: 'center'}}>
						<CircularProgress value={0} inActiveStrokeColor={'#6EB3CD'}  activeStrokeColor={'#2F6A88'} />
						<Text style={{marginTop:20}}> Pasos</Text>
					</View>
					
					<View style={{alignItems: 'center',justifyContent: 'center'}}>
						<CircularProgress value={0} inActiveStrokeColor={'#FF9467'}  activeStrokeColor={'#EB6C36'} />
						<Text style={{marginTop:20}}> Caminata</Text>
					</View>
				</View>
			</View>

			<View style={styles.smallContainer}>
				<Text style={{marginTop:20,fontWeight:"bold"}}> Tus objetivos diarios</Text>
                <Text style={{marginTop:10,fontSize:13}}> Progreso de Hoy</Text>
			</View>
			
			<View style={styles.smallContainer}>
				<Text style={{marginTop:20,fontWeight:"bold"}}> Tus objetivos semanales</Text>
                <Text style={{marginTop:10,fontSize:13}}> Progreso de Hoy</Text>
			</View>
			
			<View style={styles.smallContainer}>
				<Text style={{marginTop:20,fontWeight:"bold"}}> Tu resumen semanal</Text>
                <Text style={{marginTop:10,fontSize:13}}> {startWeek} - {endWeek}</Text>
			</View>

        </View>

        
    );
}

const styles = StyleSheet.create({
	container: {
		flex: 1,
		backgroundColor: '#F0F3F4',
		alignItems: 'center',
		justifyContent: 'center',
	},
	smallContainer: {
		marginTop: 20,
		padding:10,
		/* width:Dimensions.get('window').width, */
		width:'90%',
		borderRadius:20,
		backgroundColor: '#fff',
/* 		alignItems: 'center',
		justifyContent: 'center', */
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
        alignItems: "center",
        shadowColor: "#000",
        shadowOffset: {
          width: 0,
          height: 2
        },
        shadowOpacity: 0.25,
        shadowRadius: 4,
        elevation: 5
      },

});

export default HomeScreen;