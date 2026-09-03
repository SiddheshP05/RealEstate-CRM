$ErrorActionPreference = 'Stop'
$projectRoot = Split-Path -Parent $MyInvocation.MyCommand.Path
$portableBin = Join-Path $projectRoot 'postgresql-native\pgsql\bin'
$installedBin = 'C:\Program Files\PostgreSQL\16\bin'
$pgBin = if (Test-Path -LiteralPath (Join-Path $portableBin '..\share\postgres.bki')) { $portableBin } else { $installedBin }
$dataDir = Join-Path $projectRoot 'postgres-data'
$logFile = Join-Path $projectRoot 'postgres-native.log'
$dbName = if ($env:POSTGRES_DB) { $env:POSTGRES_DB } else { 'sai_vandan_crm' }
$dbUser = if ($env:POSTGRES_USER) { $env:POSTGRES_USER } else { 'crm' }
$dbPassword = if ($env:POSTGRES_PASSWORD) { $env:POSTGRES_PASSWORD } else { 'crm' }
$psql = Join-Path $pgBin 'psql.exe'
$createdb = Join-Path $pgBin 'createdb.exe'

if (!(Test-Path -LiteralPath (Join-Path $pgBin 'initdb.exe'))) {
  throw "PostgreSQL 16 was not found. Run the PostgreSQL setup step first."
}

$env:PGPASSWORD = $dbPassword

# Reuse an already-running native PostgreSQL installation instead of starting
# a second server on port 5432.
if (Test-NetConnection localhost -Port 5432 -InformationLevel Quiet) {
  $adminPassword = if ($env:POSTGRES_ADMIN_PASSWORD) { $env:POSTGRES_ADMIN_PASSWORD } else { 'crm' }
  $env:PGPASSWORD = $adminPassword
  $roleResult = & $psql -h localhost -p 5432 -U postgres -d postgres -tAc "SELECT 1 FROM pg_roles WHERE rolname = '$dbUser'" 2>$null
  $roleExists = (($roleResult -join '')).Trim()
  if ($roleExists -ne '1') {
    & $psql -h localhost -p 5432 -U postgres -d postgres -c "CREATE ROLE $dbUser LOGIN PASSWORD '$dbPassword';" | Out-Null
  } else {
    & $psql -h localhost -p 5432 -U postgres -d postgres -c "ALTER ROLE $dbUser WITH LOGIN PASSWORD '$dbPassword';" | Out-Null
  }
  $env:PGPASSWORD = $adminPassword
  $dbResult = & $psql -h localhost -p 5432 -U postgres -d postgres -tAc "SELECT 1 FROM pg_database WHERE datname = '$dbName'" 2>$null
  if ((($dbResult -join '')).Trim() -ne '1') {
    & $createdb -h localhost -p 5432 -U postgres -O $dbUser $dbName | Out-Null
    if ($LASTEXITCODE -ne 0) { throw "Database '$dbName' could not be created." }
  }
  Write-Host "Using existing native PostgreSQL server on localhost:5432" -ForegroundColor Green
  Write-Host "Database: $dbName | User: $dbUser" -ForegroundColor Green
  exit 0
}

if (!(Test-Path -LiteralPath (Join-Path $dataDir 'PG_VERSION'))) {
  New-Item -ItemType Directory -Force -Path $dataDir | Out-Null
  $passwordFile = Join-Path $env:TEMP 'sai-vandan-postgres-password.txt'
  [System.IO.File]::WriteAllText($passwordFile, $dbPassword)
  try {
    & (Join-Path $pgBin 'initdb.exe') -D $dataDir -U $dbUser -A scram-sha-256 --pwfile=$passwordFile --encoding=UTF8
    if ($LASTEXITCODE -ne 0) { throw 'PostgreSQL cluster initialization failed.' }
  } finally {
    Remove-Item -LiteralPath $passwordFile -Force -ErrorAction SilentlyContinue
  }
}

& (Join-Path $pgBin 'pg_ctl.exe') -D $dataDir status *> $null
if ($LASTEXITCODE -ne 0) {
  & (Join-Path $pgBin 'pg_ctl.exe') -D $dataDir -l $logFile -o "-p 5432" start
  if ($LASTEXITCODE -ne 0) { throw "PostgreSQL could not start. Check $logFile" }
  Start-Sleep -Seconds 2
}

& (Join-Path $pgBin 'createdb.exe') -h localhost -p 5432 -U $dbUser $dbName 2>$null
if ($LASTEXITCODE -ne 0) { Write-Host "Database '$dbName' already exists or could not be created." -ForegroundColor Yellow }
Write-Host "Native PostgreSQL is running on localhost:5432" -ForegroundColor Green
Write-Host "Database: $dbName | User: $dbUser" -ForegroundColor Green
