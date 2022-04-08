<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Java Mail</title>
</head>

<style>

#nsei {
    width: 100%;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-around;
}

</style>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td align="center" valign="top" bgcolor="#838383"
            style="background-color: #838383;"><br> <br>
            <table width="600" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td align="center" valign="top" bgcolor="#d3be6c"
                        style="background-color: #8B0000; font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #000000; padding: 0px 15px 10px 15px;">

                        <div style="font-size: 48px; color:rgb(255, 255, 255);">
                            <b>Olá ${user}</b>
                        </div>

                        <div style="font-size: 24px; color: #ffffff;">
                            <br>Você deu match com o (a) ${usernameMatched}</b> <br>
                        </div>
                        <div style="font-size: 12px; color: #ffffff;">
                            <br>Qualquer dúvida é só contatar o suporte pelo e-mail ${from} <br> <br>
                            <br> <br> 
                            <div id="nsei">
                                <div>
                                    <b>Att, <br>
                                        Sistema.</b>
                                </div>
                                    
                                
                               
                                    <img src="logo.png" alt="" width="200px" height="190px">
                            </div>
                            
                            <br>
                           
                        </div>
                    </td>
                </tr>
            </table> <br> <br></td>
    </tr>
</table>
</body>
</html>