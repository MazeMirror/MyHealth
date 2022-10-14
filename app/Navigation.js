import React from "react";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
//screens
import HomeScreen from "./screens/Homescreen";
import Icon from 'react-native-vector-icons/AntDesign';
import { Image } from "react-native";
import MealPlan from "./screens/MealPlan";

//import MaterialCommunityIcons from 'react-native-vector-icons/AntDesign';


const HomeStackNavigator=createNativeStackNavigator();
const Tab = createBottomTabNavigator();

function MyStack(){
    return(
        <HomeStackNavigator.Navigator
            initialRouteName="HomeScreen"
        >
            <HomeStackNavigator.Screen
                name="Home"
                component={HomeScreen}
                /* options={{ 
                    headerTitle: (props) => ( // App Logo
                     <Image
                       style={{ width: 200, height: 50 }}
                       source={require('./assets/favicon.png')}
                       resizeMode='contain'
                     />
                   ),
                   headerTitleStyle: { flex: 1, textAlign: 'center' },
                   }} */
            />
{/*             <HomeStackNavigator.Screen
                name="Plan Alimenticio"
                component={MealPlan}
            /> */}
        </HomeStackNavigator.Navigator>
    )
}

function MyTabs() {
    return (
        <Tab.Navigator 
        initialRouteName="Home"
        screenOptions={{
            tabBarActiveTintColor:"#FF7E10"
        }}>
            <Tab.Screen 
            name="Home" 
            component={MyStack} 
            options={{
                headerShown:false,
                tabBarIcon:({color,size})=>(
                    <Icon name="home" color={color} size={size} /> 
                    
                )  
            }}
            />

            <Tab.Screen 
            name="Plan Alimenticio" 
            component={MealPlan} 
            options={{
                headerShown:false,
                tabBarIcon:({color,size})=>(
                    <Image name="home" color={color} size={size} source={require('./assets/diet.png')}/> 
                    
                )  
            }}
            />
           {/*  <Tab.Screen name="Settings" component={SettingsScreen} /> */}
        </Tab.Navigator>
    );
}

export default function Navigation() {
    return (
        <NavigationContainer>
            <MyTabs />
        </NavigationContainer>
    );
}