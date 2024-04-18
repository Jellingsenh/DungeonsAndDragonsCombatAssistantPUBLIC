if (!
    #current role
    (New-Object Security.Principal.WindowsPrincipal(
        [Security.Principal.WindowsIdentity]::GetCurrent()
    #is admin?
    )).IsInRole(
        [Security.Principal.WindowsBuiltInRole]::Administrator
    )
) {
    #elevate script and exit current non-elevated runtime
    Start-Process `
        -FilePath 'powershell' `
        -ArgumentList (
            #flatten to single array
            '-File', $MyInvocation.MyCommand.Source, $args `
            | %{ $_ }
        ) `
        -Verb RunAs
    exit
}

function Start-Postgres {
    echo "Starting Database"
    $OpenDBExe = "{0}pg_ctl.exe" -f $PostgresBinaryDirectory
    & $OpenDBExe @('-D', "$DestinationPath\Data", 'start')
}

#echo "Extracting necessary files..."

function New-Postgres {
    echo "Installing and Extracting Postgres"
    $params = @{ DownloadLatest = $True }
    echo $PSScriptRoot
    & $PSScriptRoot/installPostgres.ps1 @params
} 
   

echo "Checking for DnD Combat Assistant Database..."
$DestinationPath = "C:\Postgres"
$PG_SERVERHOME = "$DestinationPath\Server"
$DnDDatabaseDisplayName = "DnD Combat Assistant Database"
$PostgresBinaryDirectory = "$PG_SERVERHOME\bin\"

$ServiceName = "Postgres"
$PG_ADMINHOME = "$DestinationPath\Admin"

if (Test-Path $DestinationPath) {
    Start-Postgres
}
else{
    New-Postgres
}
