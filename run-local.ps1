$ErrorActionPreference = 'Stop'
$projectRoot = Split-Path -Parent $MyInvocation.MyCommand.Path
$backend = Join-Path $projectRoot 'backend'
$frontend = Join-Path $projectRoot 'frontend'
$envFile = Join-Path $projectRoot '.env'

if (Test-Path -LiteralPath $envFile) {
  Get-Content -LiteralPath $envFile | ForEach-Object {
    if ($_ -match '^\s*([^#=][^=]*)=(.*)$') {
      [Environment]::SetEnvironmentVariable($matches[1].Trim(), $matches[2].Trim(), 'Process')
    }
  }
}

$javaHome = 'C:\Program Files\Eclipse Adoptium\jdk-17.0.20.101-hotspot'
$mavenHome = Join-Path $projectRoot '..\..\tools\apache-maven-3.9.16'
if (Test-Path -LiteralPath (Join-Path $javaHome 'bin\java.exe')) { $env:JAVA_HOME = $javaHome; $env:Path = "$javaHome\bin;$env:Path" }
if (Test-Path -LiteralPath (Join-Path $mavenHome 'bin\mvn.cmd')) { $env:MAVEN_HOME = (Resolve-Path $mavenHome).Path; $env:Path = "$env:MAVEN_HOME\bin;$env:Path" }

Write-Host 'Checking local tools...' -ForegroundColor Cyan
java -version
mvn -version
node --version

Write-Host 'Starting native persistent PostgreSQL...' -ForegroundColor Green
& (Join-Path $projectRoot 'setup-native-postgres.ps1')

Write-Host 'Starting backend with PostgreSQL...' -ForegroundColor Green
Start-Process powershell -ArgumentList '-NoExit','-Command',"Set-Location '$backend'; mvn spring-boot:run '-Dspring-boot.run.profiles=local'" -WindowStyle Normal

Write-Host 'Starting frontend...' -ForegroundColor Green
Start-Process powershell -ArgumentList '-NoExit','-Command',"Set-Location '$frontend'; pnpm install; pnpm run dev" -WindowStyle Normal

Write-Host 'Project is starting.' -ForegroundColor Green
Write-Host 'Frontend: http://localhost:5173' -ForegroundColor White
Write-Host 'Backend:  http://localhost:8080' -ForegroundColor White
Write-Host 'Login:    admin / admin123' -ForegroundColor White
