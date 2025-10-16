import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { ArrowLeft, Briefcase, Phone, User } from "lucide-react";
import CompanyAutocomplete from "@/components/CompanyAutocomplete";
import api from "@/axios";
import { Eye, EyeOff } from "lucide-react";


const Signup = () => {
  const navigate = useNavigate();
  const [userType, setUserType] = useState<"jobseeker" | "recruiter">("jobseeker");
  const [showPassword, setShowPassword] = useState(false);

  const [formData, setFormData] = useState({
    name: "",
    email: "",
    password: "",
    phone: "",
    company: "",
  });

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try{
      const endpoint=userType === "jobseeker" ? "/auth/v1/candidate/register" : "auth/v1/recruiter/registerExistingcompany";

      const payload=userType==="jobseeker"?{
        name: formData.name,
        email: formData.email,
        phone: formData.phone,
        password: formData.password,
      }:
      {
        name: formData.name,
        email: formData.email,
        phone: formData.phone,
        company: formData.company,
        password: formData.password,
      };
       const response = await api.post(endpoint, payload);
       console.log("Signup successful:", response.data);

    // redirect to login page after successful signup
    navigate("/login");
  } 
  catch (error: any) {
    console.error("Signup error:", error.response?.data || error.message);
    alert(error.response?.data?.message || "Signup failed");
  }
};

console.log("company id", formData.company);
console.log("user type", userType);
    
  return (
    <div className="min-h-screen flex items-center justify-center gradient-hero p-4">
      <div className="w-full max-w-md animate-fade-in">
        <Link to="/" className="inline-flex items-center text-sm text-muted-foreground hover:text-foreground mb-8 transition-colors">
          <ArrowLeft className="mr-2 h-4 w-4" />
          Back to home
        </Link>

        <Card className="glass-card shadow-glow">
          <CardHeader className="space-y-1">
            <div className="flex items-center space-x-2 mb-4">
              <div className="h-8 w-8 rounded-lg gradient-primary" />
              <span className="text-xl font-bold bg-gradient-to-r from-primary to-primary-glow bg-clip-text text-transparent">
                Algohire
              </span>
            </div>
            <CardTitle className="text-2xl font-bold">Create an account</CardTitle>
            <CardDescription>
              Join thousands finding their perfect match
            </CardDescription>
          </CardHeader>
          <CardContent>
            <Tabs value={userType} onValueChange={(v) => setUserType(v as "jobseeker" | "recruiter")} className="mb-6">
              <TabsList className="grid w-full grid-cols-2">
                <TabsTrigger value="jobseeker" className="space-x-2">
                  <User className="h-4 w-4" />
                  <span>Job Seeker</span>
                </TabsTrigger>
                <TabsTrigger value="recruiter" className="space-x-2">
                  <Briefcase className="h-4 w-4" />
                  <span>Recruiter</span>
                </TabsTrigger>
              </TabsList>

              <TabsContent value="jobseeker" className="mt-6">
                <form onSubmit={handleSubmit} className="space-y-4">
                  <div className="space-y-2">
                    <Label htmlFor="name">Full Name</Label>
                    <Input
                      id="name"
                      placeholder="John Doe"
                      value={formData.name}
                      onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                      required
                    />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="email">Email</Label>
                    <Input
                      id="email"
                      type="email"
                      placeholder="name@example.com"
                      value={formData.email}
                      onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                      required
                    />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="phone">Phone</Label>
                    <Input
                      id="phone"
                      type="tel"
                      placeholder="9967839392"
                      value={formData.phone}
                      onChange={(e) => setFormData({ ...formData, phone: e.target.value })}
                      required
                    />
                  </div>
                 <div className="relative">
  <Input
    id="password"
    type={showPassword ? "text" : "password"}
    value={formData.password}
    onChange={(e) => setFormData({ ...formData, password: e.target.value })}
    required
  />
  <button
    type="button"
    className="absolute right-3 top-1/2 transform -translate-y-1/2 text-muted-foreground"
    onClick={() => setShowPassword(!showPassword)}
  >
    {showPassword ? <EyeOff size={18} /> : <Eye size={18} />}
  </button>
</div>
                  <Button type="submit" className="w-full gradient-primary text-white hover:opacity-90 transition-smooth">
                    Create Job Seeker Account
                  </Button>
                </form>
              </TabsContent>

              <TabsContent value="recruiter" className="mt-6">
                <form onSubmit={handleSubmit} className="space-y-4">
                  <div className="space-y-2">
                    <Label htmlFor="recruiter-name">Full Name</Label>
                    <Input
                      id="recruiter-name"
                      placeholder="John Doe"
                      value={formData.name}
                      onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                      required
                    />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="company">Company Name</Label>
                    <CompanyAutocomplete
                      onSelect={(company) =>
                        setFormData({ ...formData, company: company.id })
                      }
                    />
                  </div>
                   <div className="space-y-2">
                    <Label htmlFor="phone">Phone</Label>
                    <Input
                      id="phone"
                      type="tel"
                      placeholder="9967839392"
                      value={formData.phone}
                      onChange={(e) => setFormData({ ...formData, phone: e.target.value })}
                      required
                    />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="recruiter-email">Email</Label>
                    <Input
                      id="recruiter-email"
                      type="email"
                      placeholder="name@company.com"
                      value={formData.email}
                      onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                      required
                    />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="recruiter-password">Password</Label>
                    <Input
                      id="recruiter-password"
                      type="password"
                      value={formData.password}
                      onChange={(e) => setFormData({ ...formData, password: e.target.value })}
                      required
                    />
                  </div>
                  <Button type="submit" className="w-full gradient-primary text-white hover:opacity-90 transition-smooth">
                    Create Recruiter Account
                  </Button>
                </form>
              </TabsContent>
            </Tabs>

            <div className="mt-6 text-center text-sm">
              <span className="text-muted-foreground">Already have an account? </span>
              <Link to="/login" className="text-primary hover:underline font-medium">
                Sign in
              </Link>
            </div>
          </CardContent>
        </Card>
      </div>
    </div>
  );
};

export default Signup;
